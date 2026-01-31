package com.example.merchant.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商家-商品分类关联表
 * @TableName mch_product_category
 */
@TableName(value ="mch_product_category")
@Data
public class MchProductCategory implements Serializable {
    /**
     * 商家id
     */
    private Long productId;

    /**
     * 商品分类id
     */
    private Long categoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}