package com.example.finance.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 账户表
 * @TableName payment_account
 */
@TableName(value ="payment_account")
@Data
public class PaymentAccount implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 账户编码(系统自动生成)
     */
    private String accountCode;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 账号
     */
    private String bankAccountNumber;

    /**
     * 开户名
     */
    private String bankAccountName;

    /**
     * 开户银行(如:中国工商银行)
     */
    private String bankName;

    /**
     * 开户支行
     */
    private String bankBranch;

    /**
     * 最后一次的支付时间
     */
    private LocalDateTime lastPaymentTime;

    /**
     * 状态(0-禁用 1-启用)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    /**
     * 删除时间
     */
    private LocalDateTime deleteAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}