package com.example.forum.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户浏览记录表
 * @TableName browsing_history
 */
@Schema(description = "用户浏览记录表")
@TableName(value ="browsing_history")
@Data
public class BrowsingHistory implements Serializable {
    /**
     * 浏览记录ID，自增主键
     */
    @Schema(description = "浏览记录ID，自增主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建日期，用于分区
     */
    @Schema(description = "创建日期，用于分区")
    private LocalDate createdDate;

    /**
     * 用户ID，关联用户表
     */
    @Schema(description = "用户ID，关联用户表")
    private Long userId;

    /**
     * 内容类型：1-论坛帖子，2-外卖商家
     */
    @Schema(description = "内容类型：1-论坛帖子，2-外卖商家")
    private Integer contentType;

    /**
     * 内容ID（论坛帖子ID或商家ID）
     */
    @Schema(description = "内容ID（论坛帖子ID或商家ID）")
    private Long contentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}