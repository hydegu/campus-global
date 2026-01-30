package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * 提现审核请求DTO（管理端）
 */
@Data
@Schema(name = "FinanceWithdrawalAuditDTO", description = "提现审核请求DTO")
public class FinanceWithdrawalAuditDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "提现记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "提现记录ID不能为空")
    private Long withdrawalId;

    @Schema(description = "审核状态：1-审核通过 2-审核拒绝", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "审核状态不能为空")
    private Integer status;

    @Schema(description = "审核备注", example = "审核通过，可以打款")
    private String auditRemark;
}