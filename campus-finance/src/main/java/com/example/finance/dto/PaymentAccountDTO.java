package com.example.finance.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "账户DTO")
public class PaymentAccountDTO {
    /**
     * 账户名称
     * account_name
     */
    @Schema(description = "账户名称")
    private String accountName;

    /**
     * 账户编码
     * account_code
     */
    @Schema(description = "账户编码")
    private String accountCode;

    /**
     * 联系电话
     * contact_phone
     */
    @Schema(description = "联系电话")
    private String contactPhone;

    /**
     * 联系邮箱
     * contact_email
     */
    @Schema(description = "联系邮箱")
    private String contactEmail;

    /**
     * 账号
     * bank_account_number
     */
    @Schema(description = "账号")
    private String bankAccountNumber;

    /**
     * 开户名
     * bank_account_name
     */
    @Schema(description = "开户名")
    private String bankAccountName;

    /**
     * 开户银行
     * bank_name
     */
    @Schema(description = "开户银行")
    private String bankName;

    /**
     * 开户支行
     * bank_branch
     */
    @Schema(description = "开户支行")
    private String bankBranch;

    /**
     * 状态
     * status
     */
    @Schema(description = "状态(0-禁用 1-启用)")
    private Integer status;

    /**
     * 备注
     * remark
     */
    @Schema(description = "备注")
    private String remark;
}
