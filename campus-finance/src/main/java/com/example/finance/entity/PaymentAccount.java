package com.example.finance.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 这是账户表  payment_account
 */
@Data
@Schema(description = "账户")
public class PaymentAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "主键id")
    private Long id;

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
     * 最后一次的支付时间
     * last_payment_time
     */
    @Schema(description = "最后一次的支付时间")
    private LocalDateTime lastPaymentTime;

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

    /**
     * 创建时间
     * create_at
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private LocalDateTime createAt;

    /**
     * 更新时间
     * update_at
     */
    @TableField(fill = FieldFill.UPDATE)
    @Schema(description = "更新时间")
    private LocalDateTime updateAt;

    /**
     * 删除时间
     * delete_at
     */
    @TableLogic
    @Schema(description = "删除时间")
    private LocalDateTime deleteAt;
}