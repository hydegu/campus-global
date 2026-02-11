package com.example.forum.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ForumPostQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Schema(description = "帖子ID", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "用户ID", example = "1001")
    private Long userId;

    @NotNull
    @Schema(description = "用户名", example = "张三")
    private String username;

    @NotNull
    @Schema(description = "用户头像URL", example = "https://example.com/avatar.jpg")
    private String avatarUrl;

    @NotNull
    @Schema(description = "帖子标题", example = "校园生活的美好时光")
    private String postTitle;

    @NotNull
    @Schema(description = "帖子内容", example = "今天在校园里遇到了很多有趣的事情...")
    private String postContent;
    
    @Schema(description = "图片URL列表", example = "[\"image1.jpg\", \"image2.jpg\"]")
    private List<String> imageUrls;


    @Schema(description = "浏览次数", example = "100")
    private Integer viewCount;

    @Schema(description = "点赞数", example = "50")
    private Integer likeCount;

    @Schema(description = "分享数", example = "10")
    private Integer shareCount;

    @Schema(description = "收藏数", example = "20")
    private Integer favoriteCount;

    @Schema(description = "评论数", example = "30")
    private Integer commentCount;

    @Schema(description = "创建时间", example = "2023-09-01T10:00:00")
    private LocalDateTime createAt;

    @Schema(description = "删除时间", example = "2023-09-15T10:00:00")
    private LocalDateTime deleteAt;
}