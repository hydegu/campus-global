package com.example.merchant.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品-规格中间表
 * @TableName mch_product_spec
 */
@TableName(value ="mch_product_spec")
@Data
public class MchProductSpec implements Serializable {
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 规格id
     */
    private Long specId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}