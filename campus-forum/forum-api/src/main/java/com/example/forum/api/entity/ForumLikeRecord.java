package com.example.forum.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ForumLikeRecord {

    @Schema(description = "ID")
    private Long id;
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "类型 1=活动, 2=帖子, 3=活动评论, 4=帖子评论")
    private Integer likeType;
    
    @Schema(description = "关联ID")
    private Long likeId;
    
    @Schema(description = "创建时间")
    private LocalDateTime createAt;
}