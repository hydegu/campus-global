package com.example.finance.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.BusinessException;
import com.example.finance.api.dto.PaymentAccountAddDTO;
import com.example.finance.api.dto.PaymentAccountQueryDTO;
import com.example.finance.api.dto.PaymentAccountUpdateDTO;
import com.example.finance.api.entity.PaymentAccount;
import com.example.finance.api.vo.PaymentAccountVO;
import com.example.finance.biz.mapper.PaymentAccountMapper;
import com.example.finance.biz.service.PaymentAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * @author 22417
 * @description 针对表【payment_account(账户表)】的数据库操作Service实现
 * @createDate 2026-01-30 11:38:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentAccountServiceImpl extends ServiceImpl<PaymentAccountMapper, PaymentAccount>
        implements PaymentAccountService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addAccount(PaymentAccountAddDTO addDTO) {
        // 1. 生成账户编码
        String accountCode = generateAccountCode();

        // 2. 组装账户实体
        PaymentAccount account = new PaymentAccount();
        account.setAccountName(addDTO.getAccountName());
        account.setAccountCode(accountCode);
        account.setContactPhone(addDTO.getContactPhone());
        account.setContactEmail(addDTO.getContactEmail());
        account.setBankAccountNumber(addDTO.getBankAccountNumber());
        account.setBankAccountName(addDTO.getBankAccountName());
        account.setBankName(addDTO.getBankName());
        account.setBankBranch(addDTO.getBankBranch());
        account.setStatus(1); // 1-启用
        account.setCreateAt(LocalDateTime.now());
        account.setUpdateAt(LocalDateTime.now());

        // 3. 插入数据库
        baseMapper.insert(account);

        log.info("添加平台账户成功，账户ID：{}，账户编码：{}", account.getId(), accountCode);

        return account.getId();
    }

    @Override
    public Page<PaymentAccountVO> listByQuery(PaymentAccountQueryDTO queryDTO) {
        // 1. 构建查询条件
        LambdaQueryWrapper<PaymentAccount> wrapper = Wrappers.lambdaQuery();
        wrapper.isNull(PaymentAccount::getDeleteAt);

        if (queryDTO.getAccountName() != null) {
            wrapper.like(PaymentAccount::getAccountName, queryDTO.getAccountName());
        }
        if (queryDTO.getStatus() != null) {
            wrapper.eq(PaymentAccount::getStatus, queryDTO.getStatus());
        }

        wrapper.orderByDesc(PaymentAccount::getCreateAt);

        // 2. 分页查询
        Page<PaymentAccount> page = baseMapper.selectPage(
                new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()), wrapper);

        // 3. 组装VO
        Page<PaymentAccountVO> result = new Page<>();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setRecords(page.getRecords().stream()
                .map(this::buildAccountVO)
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Long accountId, Integer status) {
        if (accountId == null) {
            throw new BusinessException("INVALID_PARAM", "账户ID不能为空");
        }
        if (status == null || (status != 0 && status != 1)) {
            throw new BusinessException("INVALID_PARAM", "状态值无效");
        }

        PaymentAccount account = baseMapper.selectById(accountId);
        if (account == null || account.getDeleteAt() != null) {
            throw new BusinessException("ACCOUNT_NOT_FOUND", "账户不存在");
        }

        account.setStatus(status);
        account.setUpdateAt(LocalDateTime.now());
        baseMapper.updateById(account);

        log.info("更新账户状态成功，账户ID：{}，状态：{}", accountId, status);
    }

    /**
     * 更新账户最后支付时间
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLastPaymentTime(Long accountId) {
        if (accountId == null) {
            return;
        }

        PaymentAccount account = baseMapper.selectById(accountId);
        if (account != null && account.getDeleteAt() == null) {
            account.setLastPaymentTime(LocalDateTime.now());
            account.setUpdateAt(LocalDateTime.now());
            baseMapper.updateById(account);
        }
    }

    /**
     * 获取账户详情
     */
    @Override
    public PaymentAccountVO getDetail(Long accountId) {
        if (accountId == null) {
            throw new BusinessException("INVALID_PARAM", "账户ID不能为空");
        }

        PaymentAccount account = baseMapper.selectById(accountId);
        if (account == null || account.getDeleteAt() != null) {
            throw new BusinessException("ACCOUNT_NOT_FOUND", "账户不存在");
        }

        return buildAccountVO(account);
    }

    /**
     * 更新账户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAccount(Long accountId, PaymentAccountUpdateDTO updateDTO) {
        // 1. 参数校验
        if (accountId == null) {
            throw new BusinessException("INVALID_PARAM", "账户ID不能为空");
        }
        if (updateDTO == null) {
            throw new BusinessException("INVALID_PARAM", "更新参数不能为空");
        }

        // 2. 查询账户是否存在
        PaymentAccount account = baseMapper.selectById(accountId);
        if (account == null || account.getDeleteAt() != null) {
            throw new BusinessException("ACCOUNT_NOT_FOUND", "账户不存在");
        }

        // 3. 验证账户状态（禁用状态的账户不允许更新）
        if (account.getStatus() == 0) {
            throw new BusinessException("ACCOUNT_DISABLED", "账户已禁用，无法更新信息");
        }

        // 4. 更新字段（只更新非空字段）
        boolean needUpdate = false;
        if (updateDTO.getAccountName() != null) {
            account.setAccountName(updateDTO.getAccountName());
            needUpdate = true;
        }
        if (updateDTO.getContactPhone() != null) {
            account.setContactPhone(updateDTO.getContactPhone());
            needUpdate = true;
        }
        if (updateDTO.getContactEmail() != null) {
            account.setContactEmail(updateDTO.getContactEmail());
            needUpdate = true;
        }
        if (updateDTO.getBankAccountNumber() != null) {
            account.setBankAccountNumber(updateDTO.getBankAccountNumber());
            needUpdate = true;
        }
        if (updateDTO.getBankAccountName() != null) {
            account.setBankAccountName(updateDTO.getBankAccountName());
            needUpdate = true;
        }
        if (updateDTO.getBankName() != null) {
            account.setBankName(updateDTO.getBankName());
            needUpdate = true;
        }
        if (updateDTO.getBankBranch() != null) {
            account.setBankBranch(updateDTO.getBankBranch());
            needUpdate = true;
        }
        if (updateDTO.getRemark() != null) {
            account.setRemark(updateDTO.getRemark());
            needUpdate = true;
        }

        // 5. 如果有字段需要更新，执行更新
        if (needUpdate) {
            account.setUpdateAt(LocalDateTime.now());
            baseMapper.updateById(account);
            log.info("更新平台账户信息成功，账户ID：{}，账户编码：{}", accountId, account.getAccountCode());
        } else {
            log.info("更新平台账户信息，但无字段需要更新，账户ID：{}", accountId);
        }
    }

    /**
     * 生成账户编码
     * 格式：ACC + yyyyMMdd + 6位自增序号
     */
    private String generateAccountCode() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 查询当天最大序号
        LambdaQueryWrapper<PaymentAccount> wrapper = Wrappers.lambdaQuery();
        wrapper.likeRight(PaymentAccount::getAccountCode, "ACC" + date);
        wrapper.orderByDesc(PaymentAccount::getAccountCode);
        wrapper.last("LIMIT 1");
        PaymentAccount lastAccount = baseMapper.selectOne(wrapper);

        int seq = 1;
        if (lastAccount != null) {
            String lastCode = lastAccount.getAccountCode();
            try {
                seq = Integer.parseInt(lastCode.substring(11)) + 1;
            } catch (NumberFormatException e) {
                log.warn("解析账户编码失败：{}", lastCode);
            }
        }

        return "ACC" + date + String.format("%06d", seq);
    }

    /**
     * 构建账户VO
     */
    private PaymentAccountVO buildAccountVO(PaymentAccount account) {
        PaymentAccountVO vo = new PaymentAccountVO();
        vo.setId(account.getId());
        vo.setAccountName(account.getAccountName());
        vo.setAccountCode(account.getAccountCode());
        vo.setContactPhone(account.getContactPhone());
        vo.setContactEmail(account.getContactEmail());
        vo.setBankAccountNumber(account.getBankAccountNumber());
        vo.setBankAccountName(account.getBankAccountName());
        vo.setBankName(account.getBankName());
        vo.setBankBranch(account.getBankBranch());
        vo.setLastPaymentTime(account.getLastPaymentTime());
        vo.setStatus(account.getStatus());
        vo.setStatusName(account.getStatus() == 1 ? "启用" : "禁用");
        vo.setCreateAt(account.getCreateAt());
        return vo;
    }
}




