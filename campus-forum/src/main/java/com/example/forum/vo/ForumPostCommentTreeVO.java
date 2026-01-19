package com.example.forum.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 论坛帖子评论树状结构VO
 */
@Data
public class ForumPostCommentTreeVO {

    /**
     * 评论ID
     */
    @NotNull
    @Schema(description = "评论ID", example = "1")
    private Long id;

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
    @NotNull
    @Schema(description = "用户头像", example = "https://example.com/avatar.jpg")
    private String avatarUrl;

    /**
     * 帖子标题
     */
    @NotNull
    @Schema(description = "帖子标题", example = "校园生活的美好时光")
    private String postTitle;

    /**
     * 帖子内容
     */
    @NotNull
    @Schema(description = "帖子内容", example = "今天在校园里遇到了很多有趣的事情...")
    private String postContent;

    /**
     * 图片URL列表
     */
    @Schema(description = "图片URL列表", example = "[\"image1.jpg\", \"image2.jpg\"]")
    private List<String> imageUrls;


    /**
     * 浏览次数
     */
    @Schema(description = "浏览次数", example = "100")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @Schema(description = "点赞数", example = "50")
    private Integer likeCount;

    /**
     * 分享数
     */
    @Schema(description = "分享数", example = "10")
    private Integer shareCount;

    /**
     * 收藏数
     */
    @Schema(description = "收藏数", example = "20")
    private Integer favoriteCount;

    /**
     * 评论数
     */
    @Schema(description = "评论数", example = "30")
    private Integer commentCount;

    /**
     * 创建时间
     */
    @NotNull
    @Schema(description = "创建时间", example = "2023-09-01T10:00:00")
    private LocalDateTime createdAt;


    /**
     * 删除时间
     */
    @Schema(description = "删除时间", example = "2023-09-15T10:00:00")
    private LocalDateTime deletedAt;

}