package com.example.forum.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 帖子表
 * @TableName forum_post
 */
@Schema(description = "帖子表")
@TableName(value ="forum_post")
@Data
public class ForumPost implements Serializable {
    /**
     * 帖子ID
     */
    @Schema(description = "帖子ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 发布人ID
     */
    @Schema(description = "发布人ID")
    private Long userId;

    /**
     * 帖子标题
     */
    @Schema(description = "帖子标题")
    private String postTitle;

    /**
     * 帖子内容
     */
    @Schema(description = "帖子内容")
    private String postContent;

    /**
     * 图片URL(JSON数组)
     */
    @Schema(description = "图片URL(JSON数组)")
    @TableField(typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private List<String> imageUrls;

    /**
     * 标签(逗号分隔)
     */
    @Schema(description = "标签(逗号分隔)")
    private String tags;

    /**
     * 浏览量
     */
    @Schema(description = "浏览量")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 转发数
     */
    @Schema(description = "转发数")
    private Integer shareCount;

    /**
     * 收藏数
     */
    @Schema(description = "收藏数")
    private Integer favoriteCount;

    /**
     * 评论数
     */
    @Schema(description = "评论数")
    private Integer commentCount;

    /**
     * 审批表关联ID
     */
    @Schema(description = "审批表关联ID")
    private Long auditId;

    /**
     * 发布时间
     */
    @Schema(description = "发布时间")
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateAt;

    /**
     * 软删除时间
     */
    @Schema(description = "软删除时间")
    private LocalDateTime deleteAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}