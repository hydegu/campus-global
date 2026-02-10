package com.example.forum.api.vo;

import com.example.forum.api.dto.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ForumActivityRegistrationQueryVO extends PageQuery {

    /**
     * 报名人Id
     */
    @Schema(description = "报名人Id", example = "1")
    private Long userId;
    /**
     * 报名人姓名
     */
    @Schema(description = "报名人姓名", example = "张三")
    private String realName;
    /**
     * 头像
     */
    @Schema(description = "头像", example = "https://example.com/avatar.jpg")
    private String avatar;
    /**
     * 申请时间
     */
    @Schema(description = "申请时间", example = "2023-01-01 12:00:00")
    private LocalDateTime createAt;
    /**
     * 活动标题
     */
    @Schema(description = "活动标题", example = "活动标题")
    private String activityTitle;
    /**
     * 审核结果
     */
    @Schema(description = "审核结果", example = "1")
    private Integer auditResult;
    /**
     * 参加人数
     */
    @Schema(description = "参加人数", example = "10")
    private Integer currentParticipants;
    /**
     * 最大参加人数
     */
    @Schema(description = "最大参加人数", example = "100")
    private Integer maxParticipants;
}
