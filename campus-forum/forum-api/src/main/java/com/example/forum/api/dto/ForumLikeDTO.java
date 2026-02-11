package com.example.forum.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ForumLikeDTO {

    @Schema(description = "用户ID",example = "1")
    private Long userId;
    @Schema(description = "点赞类型(1=活动, 2=帖子, 3=活动评论, 4=帖子评论)",example = "1")
    private Integer likeType;
    @Schema(description = "LikeId",example = "1")
    private Long likeId;

}
