package com.example.forum.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ForumActivityQueryVO {

    /**
     * 活动ID
     */
    @NotNull
    @Schema(description = "活动ID", example = "1")
    private Long id;
    
    /**
     * 活动标题
     */
    @NotNull
    @Schema(description = "活动标题", example = "校园歌手大赛")
    private String activityTitle;

    /**
     * 活动内容
     */
    @NotNull
    @Schema(description = "活动内容", example = "一年一度的校园歌手大赛即将开始，欢迎同学们踊跃报名参加！")
    private String activityContent;

    /**
     * 最大报名人数
     */
    @NotNull
    @Schema(description = "最大报名人数", example = "100")
    private Integer maxParticipants;

    /**
     * 当前报名人数
     */
    @NotNull
    @Schema(description = "当前报名人数", example = "50")
    private Integer currentParticipants;

    /**
     * 活动时间
     */
    @NotNull
    @Schema(description = "活动时间", example = "2023-10-01T10:00:00")
    private LocalDateTime activityTime;

    /**
     * 照片
     */
    @Schema(description = "活动照片列表", example = "[]")
    private List<String> images;

    /**
     * 是否显示(1-显示 0-隐藏)
     */
    @Schema(description = "是否显示(1-显示 0-隐藏)",example = "1")
    private Integer isVisible;

    /**
     * 活动状态(0-草稿 1-待审核 2-已发布 3-已取消)
     */
    @Schema(description = "活动状态(0-草稿 1-待审核 2-已发布 3-已取消)",example = "1")
    private Integer status;

    /**
     * 创建时间
     */
    @NotNull
    @Schema(description = "创建时间", example = "2023-09-0110:00:00")
    private LocalDateTime createdAt;

    /**
     * 删除时间
     */
    @Schema(description = "删除时间", example = "2023-09-1510:00:00")
    private LocalDateTime deletedAt;

}