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
 * 商品规格表
 * @TableName mch_spec
 */
@TableName(value ="mch_spec")
@Data
public class MchSpec implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 规格组名
     */
    private String groupName;

    /**
     * 规格名称(如:大份、中份、小份)
     */
    private String specName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 规格图片
     */
    private String specImage;

    /**
     * 状态(0:下架 1:上架)
     */
    private Integer status;

    /**
     * 是否默认规格 0否 1是
     */
    private Integer isDefault;

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