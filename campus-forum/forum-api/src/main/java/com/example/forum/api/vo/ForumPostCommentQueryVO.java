package com.example.forum.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 论坛帖子评论查询VO
 */
@Data
public class ForumPostCommentQueryVO {

    /**
     * 评论ID
     */
    @NotNull
    @Schema(description = "评论ID", example = "1")
    private Long id;
    
    /**
     * 帖子ID
     */
    @NotNull
    @Schema(description = "帖子ID", example = "100")
    private Long postId;
    
    /**
     * 用户ID
     */
    @NotNull
    @Schema(description = "用户ID", example = "1001")
    private Long userId;
    
    /**
     * 用户名
     */
    @NotNull
    @Schema(description = "用户名", example = "张三")
    private String username;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatarUrl;

    /**
     * 父评论ID
     */
    @NotNull
    @Schema(description = "父评论ID", example = "0")
    private Long parentId;
    
    /**
     * 根评论ID
     */
    @NotNull
    @Schema(description = "根评论ID", example = "0")
    private Long rootId;
    
    /**
     * 评论内容
     */
    @NotNull
    @Schema(description = "评论内容", example = "这是一条精彩的评论")
    private String commentContent;

    /**
     * 评论层级
     */
    @NotNull
    @Schema(description = "评论层级", example = "1")
    private Integer level;

    /**
     * 点赞数
     */
    @Schema(description = "点赞数", example = "10")
    private Integer likeCount;

    /**
     * 回复数
     */
    @Schema(description = "回复数", example = "5")
    private Integer replyCount;

    /**
     * 状态
     */
    @NotNull
    @Schema(description = "状态", example = "1")
    private Integer status;

    /**
     * 创建时间
     */
    @NotNull
    @Schema(description = "创建时间", example = "2023-09-01T10:00:00")
    private LocalDateTime createAt;

    /**
     * 子评论列表
     */
    @Schema(description = "子评论列表")
    private List<ForumPostCommentQueryVO> childComments;

    /**
     * 删除时间
     */
    @Schema(description = "删除时间", example = "2023-09-15T10:00:00")
    private LocalDateTime deleteAt;
}