package com.example.service.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 服务分类管理表
 * @TableName errand_caregory
 */
@TableName(value ="errand_caregory")
@Data
public class ErrandCategory implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID（0为顶级分类）
     */
    private Long parentId;

    /**
     * 分类层级：1-一级分类，2-二级分类
     */
    private Integer level;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 排序（值越小越靠前显示）
     */
    private Integer sortOrder;

    /**
     * 是否允许线下交易：0-否，1-是
     */
    private Integer allowOfflineTrade;

    /**
     * 状态：0-禁用，1-启用
     */
    private Integer status;

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