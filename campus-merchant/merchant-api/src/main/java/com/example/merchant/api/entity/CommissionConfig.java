package com.example.merchant.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 服务分佣配置表
 * @TableName commission_config
 */
@TableName(value ="commission_config")
@Data
public class CommissionConfig implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 联表服务分类ID
     */
    private Long categoryId;

    /**
     * 配置类型：1-全局默认，2-服务分佣 3-商家分佣 4-合伙人分佣
     */
    private Integer configType;

    /**
     * 分佣比例，10代表10%
     */
    private Integer commissionRate;

    /**
     * 
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
     * 删除时间
     */
    private LocalDateTime deleteAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}