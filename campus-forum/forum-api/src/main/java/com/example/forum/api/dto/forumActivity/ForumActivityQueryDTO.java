package com.example.forum.api.dto.forumActivity;

import com.example.forum.api.dto.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 论坛活动查询DTO
 */
@Data
public class ForumActivityQueryDTO extends PageQuery {

    /**
     * 活动标题
     */
    @Schema(description = "活动标题", example = "校园歌手大赛")
    private String activityTitle;
    
    /**
     * 活动场地
     */
    @Schema(description = "活动场地", example = "大学生活动中心")
    private String activityVenue;
    
    /**
     * 报名时间
     */
    @Schema(description = "报名时间", example = "2023-09")
    private String registrationTime;

}