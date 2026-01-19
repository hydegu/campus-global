package com.example.forum.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyCommentVO {

    @Schema(description = "评论ID", example = "1")
    private Long id;

    @Schema(description = "帖子ID", example = "1001")
    private Long postId;

    @Schema(description = "帖子标题", example = "校园生活分享")
    private String postTitle;

    @Schema(description = "评论内容", example = "这是一条评论内容")
    private String commentContent;

    @Schema(description = "点赞次数", example = "5")
    private Integer likeCount;

    @Schema(description = "回复次数", example = "2")
    private Integer replyCount;

    @Schema(description = "创建时间", example = "2025-12-01T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间", example = "2025-12-01T10:00:00")
    private LocalDateTime updatedAt;
}