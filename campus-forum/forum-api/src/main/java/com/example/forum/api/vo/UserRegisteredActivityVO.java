package com.example.forum.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegisteredActivityVO {

    /**
     * 报名ID
     */
    @Schema(description = "报名ID", example = "1")
    private Long registrationId;

    /**
     * 活动ID
     */
    @Schema(description = "活动ID", example = "1001")
    private Long activityId;
    
    /**
     * 活动标题
     */
    @Schema(description = "活动标题", example = "校园音乐节")
    private String activityTitle;

    /**
     * 活动内容
     */
    @Schema(description = "活动内容", example = "年度校园音乐盛会")
    private String activityContent;

    /**
     * 活动场地
     */
    @Schema(description = "活动场地", example = "学校大礼堂")
    private String activityVenue;

    /**
     * 活动时间
     */
    @Schema(description = "活动时间", example = "2025-12-20T19:00:00")
    private LocalDateTime activityTime;

    /**
     * 报名状态(1-已报名 2-已取消 3-已签到)
     */
    @Schema(description = "报名状态(1-已报名 2-已取消 3-已签到)", example = "1")
    private Integer registrationStatus;

    /**
     * 报名时间
     */
    @Schema(description = "报名时间", example = "2025-12-01T10:00:00")
    private LocalDateTime registrationTime;

    /**
     * 最大报名人数
     */
    @Schema(description = "最大报名人数", example = "500")
    private Integer maxParticipants;

    /**
     * 当前报名人数
     */
    @Schema(description = "当前报名人数", example = "250")
    private Integer currentParticipants;
}