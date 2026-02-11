package com.example.forum.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ForumLikeVO {

    /*
    * 用户Id
     */
    @Schema(description = "用户ID", example = "1001")
    private Long userId;

    /*
    * 点赞类型(1=活动, 2=帖子, 3=活动评论, 4=帖子评论)
     */
    @Schema(description = "点赞类型", example = "1")
    private Integer likeType;

    /*
    * 点赞对象ID
     */
    @Schema(description = "点赞对象ID", example = "1")
    private Long likeId;
}
