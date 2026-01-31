package com.example.finance.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 打款记录表
 * @TableName payment_record
 */
@TableName(value ="payment_record")
@Data
public class PaymentRecord implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 打款单号
     */
    private String paymentNo;

    /**
     * 打款账户ID(关联企业账户表)
     */
    private Long payerAccountId;

    /**
     * 打款目标id
     */
    private Long targetId;

    /**
     * 打款目标类型 1-商家 2-骑手 3-合伙人
     */
    private Integer targetType;

    /**
     * 账户类型(1:银行卡; 2:微信; 3:支付宝)
     */
    private Integer accountType;

    /**
     * 收款账号
     */
    private String accountNo;

    /**
     * 收款人姓名
     */
    private String accountName;

    /**
     * 开户银行
     */
    private String bankName;

    /**
     * 开户支行
     */
    private String bankBranch;

    /**
     * 打款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 状态:0-待确认 1-待打款 2-已打款 3-打款失败
     */
    private Integer status;

    /**
     * 打款操作人
     */
    private Long payOperatorId;

    /**
     * 打款时间
     */
    private LocalDateTime payTime;

    /**
     * 第三方流水号
     */
    private String paySerialNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    private LocalDateTime createdAt;

    /**
     * 
     */
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}