package com.example.finance.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 提现详情VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "FinanceWithdrawalVO", description = "提现详情VO")
public class FinanceWithdrawalVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "提现记录ID", example = "1")
    private Long id;

    @Schema(description = "提现单号", example = "WD20240130123456789")
    private String withdrawalNo;

    @Schema(description = "用户ID", example = "1001")
    private Long userId;

    @Schema(description = "用户姓名", example = "张三")
    private String userName;

    @Schema(description = "用户电话", example = "13800138000")
    private String userPhone;

    @Schema(description = "提现金额", example = "1000.00")
    private BigDecimal amount;

    @Schema(description = "提现方式：1-微信 2-支付宝 3-银行卡", example = "3")
    private Integer withdrawType;

    @Schema(description = "提现方式名称", example = "银行卡")
    private String withdrawTypeName;

    @Schema(description = "提现账号", example = "6222021001234567890")
    private String withdrawAccount;

    @Schema(description = "提现人姓名", example = "张三")
    private String withdrawName;

    @Schema(description = "开户银行", example = "中国工商银行")
    private String bankName;

    @Schema(description = "开户支行", example = "北京朝阳支行")
    private String bankBranch;

    @Schema(description = "状态：0-待审核 1-审核通过 2-审核拒绝 3-打款中 4-打款成功 5-打款失败", example = "1")
    private Integer status;

    @Schema(description = "状态名称", example = "审核通过")
    private String statusName;

    @Schema(description = "审核备注", example = "审核通过，可以打款")
    private String auditRemark;

    @Schema(description = "打款操作人ID", example = "100")
    private Long payOperatorId;

    @Schema(description = "打款时间", example = "2024-01-30 14:00:00")
    private LocalDateTime payTime;

    @Schema(description = "商家单号", example = "M202401301234567890")
    private String outBillNo;

    @Schema(description = "微信转账单号", example = "4200001234567890123456789")
    private String transferBillNo;

    @Schema(description = "关联财务流水ID", example = "1")
    private Long transactionId;

    @Schema(description = "申请时间", example = "2024-01-30 12:00:00")
    private LocalDateTime createAt;
}