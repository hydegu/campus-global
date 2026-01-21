package com.example.forum.api.dto.forumActivity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 论坛活动添加DTO
 */
@Data
public class ForumActivityAddDTO {

    /**
     * 活动标题
     */
    @NotBlank(message = "活动标题不能为空")
    @Schema(description = "活动标题", example = "校园歌手大赛")
    private String activityTitle;

    /**
     * 活动内容
     */
    @NotBlank(message = "活动内容不能为空")
    @Schema(description = "活动内容", example = "一年一度的校园歌手大赛即将开始，欢迎同学们踊跃报名参加！")
    private String activityContent;

    /**
     * 活动场地
     */
    @NotBlank(message = "活动场地不能为空")
    @Schema(description = "活动场地", example = "大学生活动中心")
    private String activityVenue;

    /**
     * 学校ID
     */
    @NotNull(message = "学校ID不能为空")
    @Schema(description = "学校ID", example = "1001")
    private Long schoolId;

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
    @Schema(description = "报名开始时间", example = "2023-09-0100:00:00")
    private LocalDateTime registrationStartTime;

    /**
     * 报名截止时间
     */
    @NotNull(message = "报名截止时间不能为空")
    @Schema(description = "报名截止时间", example = "2023-09-3000:00:00")
    private LocalDateTime registrationEndTime;

    /**
     * 活动时间
     */
    @NotNull(message = "活动时间不能为空")
    @Schema(description = "活动时间", example = "2023-10-1500:00:00")
    private LocalDateTime activityTime;

    /**
     * 活动图片(JSON数组)
     */
    @Schema(description = "活动图片(JSON数组)", example = "[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]")
    private List<String> images;

    /**
     * 是否显示(1-显示 0-隐藏)
     */
    @NotNull(message = "是否显示不能为空")
    @Schema(description = "是否显示(1-显示 0-隐藏)", example = "1")
    private Integer isVisible;

}