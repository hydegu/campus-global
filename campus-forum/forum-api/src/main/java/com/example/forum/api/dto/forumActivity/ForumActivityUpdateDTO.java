package com.example.forum.api.dto.forumActivity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 论坛活动更新DTO
 */
@Data
public class ForumActivityUpdateDTO {
    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空")
    @Schema(description = "活动ID", example = "1")
    private Long id;
    
    /**
     * 活动标题
     */
    @NotNull(message = "活动标题不能为空")
    @Schema(description = "活动标题", example = "校园歌手大赛")
    private String activityTitle;
    
    /**
     * 活动内容
     */
    @NotNull(message = "活动内容不能为空")
    @Schema(description = "活动内容", example = "一年一度的校园歌手大赛即将开始，欢迎同学们踊跃报名参加！")
    private String activityContent;
    
    /**
     * 活动场地
     */
    @NotNull(message = "活动场地不能为空")
    @Schema(description = "活动场地", example = "大学生活动中心")
    private String activityVenue;
    
    /**
     * 学校ID
     */
    @NotNull(message = "学校ID不能为空")
    @Schema(description = "学校ID", example = "1")
    private Integer schoolId;
    
    /**
     * 最大报名人数
     */
    @NotNull(message = "最大报名人数不能为空")
    @Schema(description = "最大报名人数", example = "100")
    private Integer maxParticipants;
    
    /**
     * 报名开始时间
     */
    @NotNull(message = "报名开始时间不能为空")
    @Schema(description = "报名开始时间", example = "2023-09-01T00:00:00")
    private LocalDateTime registrationStartTime;
    
    /**
     * 报名截止时间
     */
    @NotNull(message = "报名截止时间不能为空")
    @Schema(description = "报名截止时间", example = "2023-09-30T00:00:00")
    private LocalDateTime registrationEndTime;
    
    /**
     * 活动时间
     */
    @NotNull(message = "活动时间不能为空")
    @Schema(description = "活动时间", example = "2023-10-15T00:00:00")
    private LocalDateTime activityTime;
    
    /**
     * 活动图片
     */
    @Schema(description = "活动图片", example = "activity.jpg")
    private String image;
    
    /**
     * 是否显示(1-显示 0-隐藏)
     */
    @NotNull(message = "是否显示不能为空")
    @Schema(description = "是否显示(1-显示 0-隐藏)", example = "1")
    private Integer isVisible;
}