package com.example.behavior.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 * 用户浏览记录表
 * @TableName app_browsing_history
 */
@TableName(value ="app_browsing_history")
@Data
public class AppBrowsingHistory implements Serializable {
    /**
     * 浏览记录ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建日期，用于分区
     */
    @TableId
    private LocalDate createdDate;

    /**
     * 用户ID，关联用户表
     */
    private Long userId;

    /**
     * 内容类型：1-论坛帖子，2-外卖商家
     */
    private Integer contentType;

    /**
     * 内容ID（论坛帖子ID或商家ID）
     */
    private Long contentId;

    /**
     * 内容标题（冗余）
     */
    private String contentTitle;

    /**
     * 内容图片（冗余）
     */
    private String contentImage;

    /**
     * 内容描述（冗余）
     */
    private String contentDescription;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}