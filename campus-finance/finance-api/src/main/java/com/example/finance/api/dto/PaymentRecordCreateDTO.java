package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 创建打款记录请求DTO（管理端）
 */
@Data
@Schema(name = "PaymentRecordCreateDTO", description = "创建打款记录请求DTO")
public class PaymentRecordCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "打款账户ID（平台企业账户）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "打款账户ID不能为空")
    private Long payerAccountId;

    @Schema(description = "打款目标ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "打款目标ID不能为空")
    private Long targetId;

    @Schema(description = "打款目标类型：1-商家 2-骑手 3-合伙人", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "打款目标类型不能为空")
    private Integer targetType;

    @Schema(description = "收款账户类型：1-银行卡 2-微信 3-支付宝", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "收款账户类型不能为空")
    private Integer accountType;

    @Schema(description = "收款账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "6222021001234567890")
    @NotBlank(message = "收款账号不能为空")
    private String accountNo;

    @Schema(description = "收款人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotBlank(message = "收款人姓名不能为空")
    private String accountName;

    @Schema(description = "开户银行", example = "中国工商银行")
    private String bankName;

    @Schema(description = "开户支行", example = "北京朝阳支行")
    private String bankBranch;

    @Schema(description = "打款金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "5000.00")
    @NotNull(message = "打款金额不能为空")
    @DecimalMin(value = "0.01", message = "打款金额必须大于0")
    private BigDecimal paymentAmount;

    @Schema(description = "备注", example = "商家月度结算")
    private String remark;
}