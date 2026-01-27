package com.example.merchant.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 配送费规则明细表
 * @TableName delivery_fee_rule
 */
@TableName(value ="delivery_fee_rule")
@Data
public class DeliveryFeeRule implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 关联配置表id
     */
    private Long configId;

    /**
     * 规则类型:1-距离,2-时间
     */
    private Integer ruleType;

    /**
     * 起始距离(公里)
     */
    private BigDecimal distanceStart;

    /**
     * 结束距离(公里)
     */
    private BigDecimal distanceEnd;

    /**
     * 每公里价格
     */
    private BigDecimal pricePerKm;

    /**
     * 开始时间
     */
    private LocalTime timeStart;

    /**
     * 结束时间
     */
    private LocalTime timeEnd;

    /**
     * 时段附加费
     */
    private BigDecimal timeFee;

    /**
     * 时段类型:1-白天 2-夜间
     */
    private Integer timeType;

    /**
     * 排序
     */
    private Integer sortOrder;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}