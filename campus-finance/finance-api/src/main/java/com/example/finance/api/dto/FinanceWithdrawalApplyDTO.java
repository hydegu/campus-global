package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 提现申请请求DTO（用户端）
 */
@Data
@Schema(name = "FinanceWithdrawalApplyDTO", description = "提现申请请求DTO")
public class FinanceWithdrawalApplyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "提现金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000.00")
    @NotNull(message = "提现金额不能为空")
    @DecimalMin(value = "0.01", message = "提现金额必须大于0")
    private BigDecimal amount;

    @Schema(description = "提现方式：1-微信 2-支付宝 3-银行卡", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @NotNull(message = "提现方式不能为空")
    private Integer withdrawType;

    @Schema(description = "提现账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "6222021001234567890")
    @NotBlank(message = "提现账号不能为空")
    private String withdrawAccount;

    @Schema(description = "提现人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotBlank(message = "提现人姓名不能为空")
    private String withdrawName;
}