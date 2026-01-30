package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 创建流水请求DTO
 */
@Data
@Schema(name = "FinanceTransactionAddDTO", description = "创建流水请求DTO")
public class FinanceTransactionAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED, example = "FT20240130123456789")
    @NotBlank(message = "流水号不能为空")
    private String transactionNo;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "交易类型：1-打款 2-消费 3-退款 4-提现", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "交易类型不能为空")
    private Integer transactionType;

    @Schema(description = "交易金额（正数为收入，负数为支出）", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    @NotNull(message = "交易金额不能为空")
    private BigDecimal amount;

    @Schema(description = "关联业务类型：1-订单 2-提现 3-退款 4-打款", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "关联业务类型不能为空")
    private Integer relatedType;

    @Schema(description = "关联业务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "关联业务ID不能为空")
    private Long relatedId;

    @Schema(description = "备注", example = "订单支付")
    private String remark;
}