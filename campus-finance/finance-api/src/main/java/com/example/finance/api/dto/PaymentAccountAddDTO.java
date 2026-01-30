package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加平台账户请求DTO（管理端）
 */
@Data
@Schema(name = "PaymentAccountAddDTO", description = "添加平台账户请求DTO")
public class PaymentAccountAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "账户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "平台主账户")
    @NotBlank(message = "账户名称不能为空")
    private String accountName;

    @Schema(description = "联系电话", example = "13800138000")
    private String contactPhone;

    @Schema(description = "联系邮箱", example = "finance@example.com")
    private String contactEmail;

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "6222021001234567890")
    @NotBlank(message = "账号不能为空")
    private String bankAccountNumber;

    @Schema(description = "开户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "某某公司")
    @NotBlank(message = "开户名不能为空")
    private String bankAccountName;

    @Schema(description = "开户银行", requiredMode = Schema.RequiredMode.REQUIRED, example = "中国工商银行")
    @NotBlank(message = "开户银行不能为空")
    private String bankName;

    @Schema(description = "开户支行", example = "北京朝阳支行")
    private String bankBranch;
}