package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 更新平台账户请求DTO（管理端）
 */
@Data
@Schema(name = "PaymentAccountUpdateDTO", description = "更新平台账户请求DTO")
public class PaymentAccountUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "账户名称", example = "平台主账户")
    private String accountName;

    @Schema(description = "联系电话", example = "13800138000")
    private String contactPhone;

    @Schema(description = "联系邮箱", example = "finance@example.com")
    private String contactEmail;

    @Schema(description = "账号", example = "6222021001234567890")
    private String bankAccountNumber;

    @Schema(description = "开户名", example = "某某公司")
    private String bankAccountName;

    @Schema(description = "开户银行", example = "中国工商银行")
    private String bankName;

    @Schema(description = "开户支行", example = "北京朝阳支行")
    private String bankBranch;

    @Schema(description = "备注", example = "备用账户")
    private String remark;
}