package com.example.forum.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyForumPostVO {

    @Schema(description = "帖子ID", example = "1")
    private Long id;

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

    @Schema(description = "分享次数", example = "5")
    private Integer shareCount;

    @Schema(description = "收藏次数", example = "3")
    private Integer favoriteCount;

    @Schema(description = "评论次数", example = "8")
    private Integer commentCount;

    @Schema(description = "创建时间", example = "2025-12-01T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间", example = "2025-12-01T10:00:00")
    private LocalDateTime updatedAt;
}