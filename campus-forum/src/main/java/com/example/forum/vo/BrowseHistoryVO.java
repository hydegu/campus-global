package com.example.forum.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BrowseHistoryVO {

    @Schema(description = "浏览记录ID", example = "1")
    private Long id;

    @Schema(description = "用户ID", example = "1")
    private Long userId;

    @Schema(description = "内容类型：1-论坛帖子", example = "1")
    private Integer contentType;

    @Schema(description = "内容ID（论坛帖子ID）", example = "1")
    private Long contentId;

    @Schema(description = "帖子标题", example = "校园生活分享")
    private String postTitle;

    @Schema(description = "帖子内容", example = "今天在校园里看到了美丽的风景")
    private String postContent;

    @Schema(description = "图片URL数组", example = "[\"https://example.com/image1.jpg\", \"https://example.com/image2.jpg\"]")
    private String[] imageUrls;

    @Schema(description = "标签数组", example = "[\"校园\", \"生活\", \"分享\"]")
    private String[] tags;

    @Schema(description = "浏览次数", example = "100")
    private Integer viewCount;

    @Schema(description = "点赞次数", example = "10")
    private Integer likeCount;

    @Schema(description = "评论次数", example = "8")
    private Integer commentCount;

    @Schema(description = "创建时间", example = "2025-12-01T10:00:00")
    private LocalDateTime createdAt;
}