package com.example.finance.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.api.feign.RemoteUserService;
import com.example.common.core.constant.CommonConstants;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.util.Result;
import com.example.finance.api.dto.FinanceTransactionAddDTO;
import com.example.finance.api.dto.FinanceWithdrawalApplyDTO;
import com.example.finance.api.dto.FinanceWithdrawalAuditDTO;
import com.example.finance.api.dto.FinanceWithdrawalQueryDTO;
import com.example.finance.api.entity.FinanceWithdrawal;
import com.example.finance.api.enums.RelatedTypeEnum;
import com.example.finance.api.enums.TransactionTypeEnum;
import com.example.finance.api.enums.WithdrawalStatusEnum;
import com.example.finance.api.enums.WithdrawalTypeEnum;
import com.example.finance.api.vo.FinanceWithdrawalVO;
import com.example.finance.biz.mapper.FinanceWithdrawalMapper;
import com.example.finance.biz.service.FinanceTransactionService;
import com.example.finance.biz.service.FinanceWithdrawalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author 22417
 * @description 针对表【finance_withdrawal(提现申请表)】的数据库操作Service实现
 * @createDate 2026-01-30 11:38:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FinanceWithdrawalServiceImpl extends ServiceImpl<FinanceWithdrawalMapper, FinanceWithdrawal>
        implements FinanceWithdrawalService {

    private final FinanceTransactionService financeTransactionService;
    private final RemoteUserService remoteUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long applyWithdrawal(FinanceWithdrawalApplyDTO applyDTO) {
        // 1. 获取当前用户ID（从SecurityContext，这里暂时使用固定值）
        Long currentUserId = 1001L; // TODO: 从SecurityContext获取当前用户ID

        // 2. 查询用户信息
        Result<UserInfo> userResult = remoteUserService.getUserInfoById(currentUserId);
        if (userResult.getCode() != CommonConstants.SUCCESS || userResult.getData() == null) {
            throw new BusinessException("USER_NOT_FOUND", "用户不存在");
        }
        UserInfo userInfo = userResult.getData();

        // 3. 校验提现金额
        if (applyDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("INVALID_AMOUNT", "提现金额必须大于0");
        }
        // TODO: 校验用户余额是否充足

        // 4. 生成提现单号
        String withdrawalNo = generateWithdrawalNo();

        // 5. 组装提现申请实体
        FinanceWithdrawal withdrawal = new FinanceWithdrawal();
        withdrawal.setWithdrawalNo(withdrawalNo);
        withdrawal.setUserId(currentUserId);
        withdrawal.setAmount(applyDTO.getAmount());
        withdrawal.setWithdrawType(applyDTO.getWithdrawType());
        withdrawal.setWithdrawAccount(applyDTO.getWithdrawAccount());
        withdrawal.setWithdrawName(applyDTO.getWithdrawName());
        withdrawal.setStatus(WithdrawalStatusEnum.PENDING.getCode());
        withdrawal.setAuditId(0L); // 暂时设为0，后续审核时更新
        withdrawal.setCreateAt(LocalDateTime.now());
        withdrawal.setUpdateAt(LocalDateTime.now());

        // 6. 插入提现申请
        baseMapper.insert(withdrawal);

        // 7. 创建财务流水（提现支出）
        FinanceTransactionAddDTO transactionDTO = new FinanceTransactionAddDTO();
        transactionDTO.setTransactionNo(generateTransactionNo());
        transactionDTO.setUserId(currentUserId);
        transactionDTO.setTransactionType(TransactionTypeEnum.WITHDRAWAL.getCode());
        transactionDTO.setAmount(applyDTO.getAmount().negate()); // 提现为支出，负数
        transactionDTO.setRelatedType(RelatedTypeEnum.WITHDRAWAL.getCode());
        transactionDTO.setRelatedId(withdrawal.getId());
        transactionDTO.setRemark("提现申请：" + withdrawalNo);
        Long transactionId = financeTransactionService.createTransaction(transactionDTO);

        // 8. 关联财务流水ID
        withdrawal.setTransactionId(transactionId);
        baseMapper.updateById(withdrawal);

        log.info("用户申请提现成功，用户ID：{}，提现单号：{}，金额：{}",
                currentUserId, withdrawalNo, applyDTO.getAmount());

        return withdrawal.getId();
    }

    @Override
    public Page<FinanceWithdrawalVO> listByQuery(FinanceWithdrawalQueryDTO queryDTO) {
        // 1. 构建查询条件
        LambdaQueryWrapper<FinanceWithdrawal> wrapper = Wrappers.lambdaQuery();

        if (queryDTO.getUserId() != null) {
            wrapper.eq(FinanceWithdrawal::getUserId, queryDTO.getUserId());
        }
        if (queryDTO.getStatus() != null) {
            wrapper.eq(FinanceWithdrawal::getStatus, queryDTO.getStatus());
        }
        if (queryDTO.getStartTime() != null) {
            wrapper.ge(FinanceWithdrawal::getCreateAt, queryDTO.getStartTime());
        }
        if (queryDTO.getEndTime() != null) {
            wrapper.le(FinanceWithdrawal::getCreateAt, queryDTO.getEndTime());
        }

        wrapper.orderByDesc(FinanceWithdrawal::getCreateAt);

        // 2. 分页查询
        Page<FinanceWithdrawal> page = baseMapper.selectPage(
                new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()), wrapper);

        // 3. 组装VO
        Page<FinanceWithdrawalVO> result = new Page<>();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setRecords(page.getRecords().stream()
                .map(this::buildWithdrawalVO)
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public FinanceWithdrawalVO getDetail(Long id) {
        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "提现记录ID不能为空");
        }

        FinanceWithdrawal withdrawal = baseMapper.selectById(id);
        if (withdrawal == null) {
            throw new BusinessException("WITHDRAWAL_NOT_FOUND", "提现记录不存在");
        }

        return buildWithdrawalVO(withdrawal);
    }

    /**
     * 生成提现单号
     * 格式：WD + yyyyMMddHHmmss + 8位随机大写字符
     */
    private String generateWithdrawalNo() {
        return "WD" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * 生成流水号
     */
    private String generateTransactionNo() {
        return "FT" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * 构建提现VO
     */
    private FinanceWithdrawalVO buildWithdrawalVO(FinanceWithdrawal withdrawal) {
        FinanceWithdrawalVO vo = new FinanceWithdrawalVO();
        vo.setId(withdrawal.getId());
        vo.setWithdrawalNo(withdrawal.getWithdrawalNo());
        vo.setUserId(withdrawal.getUserId());
        vo.setUserName(withdrawal.getUserName());
        vo.setUserPhone(withdrawal.getUserPhone());
        vo.setAmount(withdrawal.getAmount());
        vo.setWithdrawType(withdrawal.getWithdrawType());
        WithdrawalTypeEnum typeEnum = WithdrawalTypeEnum.getByCode(withdrawal.getWithdrawType());
        vo.setWithdrawTypeName(typeEnum != null ? typeEnum.getDesc() : "");
        vo.setWithdrawAccount(withdrawal.getWithdrawAccount());
        vo.setWithdrawName(withdrawal.getWithdrawName());
        vo.setStatus(withdrawal.getStatus());
        WithdrawalStatusEnum statusEnum = WithdrawalStatusEnum.getByCode(withdrawal.getStatus());
        vo.setStatusName(statusEnum != null ? statusEnum.getDesc() : "");
        vo.setPayOperatorId(withdrawal.getPayOperatorId());
        vo.setPayTime(withdrawal.getPayTime());
        vo.setOutBillNo(withdrawal.getOutBillNo());
        vo.setTransferBillNo(withdrawal.getTransferBillNo());
        vo.setTransactionId(withdrawal.getTransactionId());
        vo.setCreateAt(withdrawal.getCreateAt());
        return vo;
    }
}




