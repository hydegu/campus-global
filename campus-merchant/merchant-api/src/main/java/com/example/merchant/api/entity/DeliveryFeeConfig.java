package com.example.merchant.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 配送费配置主表
 * @TableName delivery_fee_config
 */
@TableName(value ="delivery_fee_config")
@Data
public class DeliveryFeeConfig implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 配置名称
     */
    private String configName;

    /**
     * 起步价
     */
    private BigDecimal baseFee;

    /**
     * 起步距离(公里，此距离内采用起步价)
     */
    private BigDecimal baseDistance;

    /**
     * 状态:0禁用,1启用
     */
    private Integer status;

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