package com.example.forum.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 帖子评论表
 * @TableName forum_post_comment
 */
@Schema(description = "帖子评论表")
@TableName(value ="forum_post_comment")
@Data
public class ForumPostComment implements Serializable {
    /**
     * 评论ID
     */
    @Schema(description = "评论ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 帖子ID
     */
    @Schema(description = "帖子ID")
    private Long postId;

    /**
     * 评论人ID
     */
    @Schema(description = "评论人ID")
    private Long userId;

    /**
     * 评论内容
     */
    @Schema(description = "评论内容")
    private String commentContent;

    /**
     * 父级评论ID(NULL表示根评论)
     */
    @Schema(description = "父级评论ID(NULL表示根评论)")
    private Long parentId;

    /**
     * 根评论ID(方便查询整个评论树)
     */
    @Schema(description = "根评论ID(方便查询整个评论树)")
    private Long rootId;

    /**
     * 评论层级(0-根评论 1-一级回复 2-二级回复)
     */
    @Schema(description = "评论层级(0-根评论 1-一级回复 2-二级回复)")
    private Integer level;

    /**
     * 点赞数
     */
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 回复数
     */
    @Schema(description = "回复数")
    private Integer replyCount;

    /**
     * 状态(1-正常 2-用户删除 3-管理员删除)
     */
    @Schema(description = "状态(1-正常 2-用户删除 3-管理员删除)")
    private Integer status;

    /**
     * 删除操作人ID
     */
    @Schema(description = "删除操作人ID")
    private Long deletedBy;

    /**
     * 软删除时间
     */
    @Schema(description = "软删除时间")
    private LocalDateTime deletedAt;

    /**
     * 评论时间
     */
    @Schema(description = "评论时间")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}