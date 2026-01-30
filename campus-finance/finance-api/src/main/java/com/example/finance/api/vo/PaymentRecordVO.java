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
 * 打款详情VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PaymentRecordVO", description = "打款详情VO")
public class PaymentRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "打款记录ID", example = "1")
    private Long id;

    @Schema(description = "打款单号", example = "PY20240130123456789")
    private String paymentNo;

    @Schema(description = "打款账户ID（平台企业账户）", example = "1")
    private Long payerAccountId;

    @Schema(description = "打款账户名称", example = "平台主账户")
    private String payerAccountName;

    @Schema(description = "打款目标ID", example = "1001")
    private Long targetId;

    @Schema(description = "打款目标类型：1-商家 2-骑手 3-合伙人", example = "1")
    private Integer targetType;

    @Schema(description = "打款目标名称", example = "张三餐厅")
    private String targetName;

    @Schema(description = "收款账户类型：1-银行卡 2-微信 3-支付宝", example = "1")
    private Integer accountType;

    @Schema(description = "收款账户类型名称", example = "银行卡")
    private String accountTypeName;

    @Schema(description = "收款账号", example = "6222021001234567890")
    private String accountNo;

    @Schema(description = "收款人姓名", example = "张三")
    private String accountName;

    @Schema(description = "开户银行", example = "中国工商银行")
    private String bankName;

    @Schema(description = "开户支行", example = "北京朝阳支行")
    private String bankBranch;

    @Schema(description = "打款金额", example = "5000.00")
    private BigDecimal paymentAmount;

    @Schema(description = "状态：0-待确认 1-待打款 2-已打款 3-打款失败", example = "2")
    private Integer status;

    @Schema(description = "状态名称", example = "已打款")
    private String statusName;

    @Schema(description = "打款操作人ID", example = "100")
    private Long payOperatorId;

    @Schema(description = "打款操作人姓名", example = "管理员")
    private String payOperatorName;

    @Schema(description = "打款时间", example = "2024-01-30 14:00:00")
    private LocalDateTime payTime;

    @Schema(description = "第三方流水号", example = "TX202401301234567890123")
    private String paySerialNo;

    @Schema(description = "备注", example = "商家月度结算")
    private String remark;

    @Schema(description = "创建时间", example = "2024-01-30 12:00:00")
    private LocalDateTime createAt;
}