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
 * 提现申请表
 * @TableName finance_withdrawal
 */
@TableName(value ="finance_withdrawal")
@Data
public class FinanceWithdrawal implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 审核记录ID
     */
    private Long auditId;

    /**
     * 提现单号
     */
    private String withdrawalNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户姓名（冗余）
     */
    private String userName;

    /**
     * 用户电话（冗余）
     */
    private String userPhone;

    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 提现方式:1-微信
     */
    private Integer withdrawType;

    /**
     * 提现账号
     */
    private String withdrawAccount;

    /**
     * 提现人姓名
     */
    private String withdrawName;

    /**
     * 状态:0-待审核 1-审核通过 2-审核拒绝 3-打款中 4-打款成功 5-打款失败
     */
    private Integer status;

    /**
     * 打款操作人ID
     */
    private Long payOperatorId;

    /**
     * 打款时间
     */
    private LocalDateTime payTime;

    /**
     * 打款备注
     */
    private String payRemark;

    /**
     * 商家单号
     */
    private String outBillNo;

    /**
     * 微信转账单号
     */
    private String transferBillNo;

    /**
     * 单据状态
     */
    private String state;

    /**
     * 关联财务流水ID
     */
    private Long transactionId;

    /**
     * 
     */
    private LocalDateTime createAt;

    /**
     * 
     */
    private LocalDateTime updateAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}