package com.example.finance.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.BusinessException;
import com.example.finance.api.dto.FinanceTransactionAddDTO;
import com.example.finance.api.dto.PaymentRecordCreateDTO;
import com.example.finance.api.dto.PaymentRecordQueryDTO;
import com.example.finance.api.entity.PaymentRecord;
import com.example.finance.api.enums.PaymentAccountTypeEnum;
import com.example.finance.api.enums.PaymentStatusEnum;
import com.example.finance.api.enums.RelatedTypeEnum;
import com.example.finance.api.enums.TargetTypeEnum;
import com.example.finance.api.enums.TransactionTypeEnum;
import com.example.finance.api.vo.PaymentAccountVO;
import com.example.finance.api.vo.PaymentRecordVO;
import com.example.finance.biz.mapper.PaymentRecordMapper;
import com.example.finance.biz.service.FinanceTransactionService;
import com.example.finance.biz.service.PaymentAccountService;
import com.example.finance.biz.service.PaymentRecordService;
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
 * @description 针对表【payment_record(打款记录表)】的数据库操作Service实现
 * @createDate 2026-01-30 11:38:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentRecordServiceImpl extends ServiceImpl<PaymentRecordMapper, PaymentRecord>
        implements PaymentRecordService {

    private final PaymentAccountService paymentAccountService;
    private final FinanceTransactionService financeTransactionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPaymentRecord(PaymentRecordCreateDTO createDTO) {
        // 1. 校验打款账户
        PaymentAccountVO account = paymentAccountService.getDetail(createDTO.getPayerAccountId());
        if (account == null || account.getStatus() != 1) {
            throw new BusinessException("ACCOUNT_NOT_AVAILABLE", "打款账户不可用");
        }

        // 2. 校验打款金额
        if (createDTO.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("INVALID_AMOUNT", "打款金额必须大于0");
        }

        // 3. 生成打款单号
        String paymentNo = generatePaymentNo();

        // 4. 组装打款记录
        PaymentRecord record = new PaymentRecord();
        record.setPaymentNo(paymentNo);
        record.setPayerAccountId(createDTO.getPayerAccountId());
        record.setTargetId(createDTO.getTargetId());
        record.setTargetType(createDTO.getTargetType());
        record.setAccountType(createDTO.getAccountType());
        record.setAccountNo(createDTO.getAccountNo());
        record.setAccountName(createDTO.getAccountName());
        record.setBankName(createDTO.getBankName());
        record.setBankBranch(createDTO.getBankBranch());
        record.setPaymentAmount(createDTO.getPaymentAmount());
        record.setStatus(PaymentStatusEnum.PENDING_CONFIRM.getCode());
        record.setRemark(createDTO.getRemark());
        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());

        // 5. 插入打款记录
        baseMapper.insert(record);

        // 6. 创建财务流水（系统打款）
        FinanceTransactionAddDTO transactionDTO = new FinanceTransactionAddDTO();
        transactionDTO.setTransactionNo(generateTransactionNo());
        transactionDTO.setUserId(createDTO.getTargetId());
        transactionDTO.setTransactionType(TransactionTypeEnum.PAYMENT.getCode());
        transactionDTO.setAmount(createDTO.getPaymentAmount()); // 打款为收入，正数
        transactionDTO.setRelatedType(RelatedTypeEnum.PAYMENT.getCode());
        transactionDTO.setRelatedId(record.getId());
        transactionDTO.setRemark("系统打款：" + paymentNo);
        financeTransactionService.createTransaction(transactionDTO);

        log.info("创建打款记录成功，打款单号：{}，目标ID：{}，目标类型：{}，金额：{}",
                paymentNo, createDTO.getTargetId(), createDTO.getTargetType(), createDTO.getPaymentAmount());

        return record.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executePayment(Long paymentId) {
        // 1. 查询打款记录
        PaymentRecord record = baseMapper.selectById(paymentId);
        if (record == null) {
            throw new BusinessException("PAYMENT_NOT_FOUND", "打款记录不存在");
        }

        // 2. 校验状态
        if (!PaymentStatusEnum.PENDING_PAYMENT.getCode().equals(record.getStatus())) {
            throw new BusinessException("INVALID_STATUS", "打款记录状态不允许执行");
        }

        // 4. 调用第三方支付接口
        try {
            String paySerialNo = callThirdPartyPayment(record);

            // 5. 更新打款成功
            record.setStatus(PaymentStatusEnum.PAID.getCode());
            record.setPaySerialNo(paySerialNo);
            record.setPayTime(LocalDateTime.now());
            record.setPayOperatorId(1001L); // TODO: 从SecurityContext获取当前用户ID
            record.setUpdatedAt(LocalDateTime.now());
            baseMapper.updateById(record);

            // 6. 更新账户最后支付时间
            paymentAccountService.updateLastPaymentTime(record.getPayerAccountId());

            log.info("打款成功，打款单号：{}，第三方流水号：{}", record.getPaymentNo(), paySerialNo);

        } catch (Exception e) {
            // 打款失败
            record.setStatus(PaymentStatusEnum.FAILED.getCode());
            record.setUpdatedAt(LocalDateTime.now());
            baseMapper.updateById(record);

            log.error("打款失败，打款单号：{}", record.getPaymentNo(), e);
            throw new BusinessException("PAYMENT_FAILED", "打款失败：" + e.getMessage());
        }
    }

    @Override
    public Page<PaymentRecordVO> listByQuery(PaymentRecordQueryDTO queryDTO) {
        // 1. 构建查询条件
        LambdaQueryWrapper<PaymentRecord> wrapper = Wrappers.lambdaQuery();

        if (queryDTO.getTargetId() != null) {
            wrapper.eq(PaymentRecord::getTargetId, queryDTO.getTargetId());
        }
        if (queryDTO.getTargetType() != null) {
            wrapper.eq(PaymentRecord::getTargetType, queryDTO.getTargetType());
        }
        if (queryDTO.getStatus() != null) {
            wrapper.eq(PaymentRecord::getStatus, queryDTO.getStatus());
        }
        if (queryDTO.getStartTime() != null) {
            wrapper.ge(PaymentRecord::getCreatedAt, queryDTO.getStartTime());
        }
        if (queryDTO.getEndTime() != null) {
            wrapper.le(PaymentRecord::getCreatedAt, queryDTO.getEndTime());
        }

        wrapper.orderByDesc(PaymentRecord::getCreatedAt);

        // 2. 分页查询
        Page<PaymentRecord> page = baseMapper.selectPage(
                new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()), wrapper);

        // 3. 组装VO
        Page<PaymentRecordVO> result = new Page<>();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setRecords(page.getRecords().stream()
                .map(this::buildPaymentRecordVO)
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public PaymentRecordVO getDetail(Long id) {
        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "打款记录ID不能为空");
        }

        PaymentRecord record = baseMapper.selectById(id);
        if (record == null) {
            throw new BusinessException("PAYMENT_NOT_FOUND", "打款记录不存在");
        }

        return buildPaymentRecordVO(record);
    }

    /**
     * 生成打款单号
     * 格式：PY + yyyyMMddHHmmss + 8位随机大写字符
     */
    private String generatePaymentNo() {
        return "PY" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
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
     * 调用第三方支付接口
     */
    private String callThirdPartyPayment(PaymentRecord record) {
        // TODO: 根据accountType调用对应的第三方支付接口
        // PaymentAccountTypeEnum.BANK -> 银行转账
        // PaymentAccountTypeEnum.WECHAT -> 微信企业付款
        // PaymentAccountTypeEnum.ALIPAY -> 支付宝转账

        return "MOCK_SERIAL_NO_" + System.currentTimeMillis();
    }

    /**
     * 构建打款记录VO
     */
    private PaymentRecordVO buildPaymentRecordVO(PaymentRecord record) {
        PaymentRecordVO vo = new PaymentRecordVO();
        vo.setId(record.getId());
        vo.setPaymentNo(record.getPaymentNo());
        vo.setPayerAccountId(record.getPayerAccountId());
        vo.setTargetId(record.getTargetId());
        vo.setTargetType(record.getTargetType());
        TargetTypeEnum targetTypeEnum = TargetTypeEnum.getByCode(record.getTargetType());
        vo.setTargetName(targetTypeEnum != null ? targetTypeEnum.getDesc() : "");
        vo.setAccountType(record.getAccountType());
        PaymentAccountTypeEnum accountTypeEnum = PaymentAccountTypeEnum.getByCode(record.getAccountType());
        vo.setAccountTypeName(accountTypeEnum != null ? accountTypeEnum.getDesc() : "");
        vo.setAccountNo(record.getAccountNo());
        vo.setAccountName(record.getAccountName());
        vo.setBankName(record.getBankName());
        vo.setBankBranch(record.getBankBranch());
        vo.setPaymentAmount(record.getPaymentAmount());
        vo.setStatus(record.getStatus());
        PaymentStatusEnum statusEnum = PaymentStatusEnum.getByCode(record.getStatus());
        vo.setStatusName(statusEnum != null ? statusEnum.getDesc() : "");
        vo.setPayOperatorId(record.getPayOperatorId());
        vo.setPayTime(record.getPayTime());
        vo.setPaySerialNo(record.getPaySerialNo());
        vo.setRemark(record.getRemark());
        return vo;
    }
}




