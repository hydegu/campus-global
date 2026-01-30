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
 * 财务流水表
 * @TableName finance_transaction
 */
@TableName(value ="finance_transaction")
@Data
public class FinanceTransaction implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 流水号
     */
    private String transactionNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 交易类型:1-打款 2-消费 3-退款 4-提现
     */
    private Integer transactionType;

    /**
     * 交易金额(正数为收入,负数为支出)
     */
    private BigDecimal amount;

    /**
     * 关联业务类型:1-订单 2-提现 3-退款
     */
    private Integer relatedType;

    /**
     * 关联业务ID
     */
    private Long relatedId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    private LocalDateTime createAt;

    /**
     * 
     */
    private LocalDateTime updateAt;

    /**
     * 
     */
    private LocalDateTime deleteAt;

    /**
     * 用户姓名（冗余）
     */
    private String userName;

    /**
     * 用户电话（冗余）
     */
    private String userPhone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}