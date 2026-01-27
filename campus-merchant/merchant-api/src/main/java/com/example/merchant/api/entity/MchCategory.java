package com.example.merchant.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 商品分类表
 * @TableName mch_category
 */
@TableName(value ="mch_category")
@Data
public class MchCategory implements Serializable {
    /**
     * 分类ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 上级分类ID（0表示顶级分类）
     */
    private Long parentId;

    /**
     * 分类层级（1-一级，2-二级 3-三级)
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态:0-禁用,1-启用
     */
    private Integer status;

    /**
     * 0-全局分类 1-商家自定义分类
     */
    private Integer isGlobal;

    /**
     * 商家id
     */
    private Long mchId;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    /**
     * 
     */
    private LocalDateTime deleteAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}