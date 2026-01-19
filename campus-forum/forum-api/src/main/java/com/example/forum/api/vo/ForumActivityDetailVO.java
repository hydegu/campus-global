package com.example.forum.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 论坛活动详情VO
 */
@Data
public class ForumActivityDetailVO {

    /**
     * 活动ID
     */
    @NotNull
    @Schema(description = "活动ID", example = "1")
    @TableId(type = IdType.AUTO)
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
     * 活动场地
     */
    @NotNull
    @Schema(description = "活动场地", example = "大学生活动中心")
    private String activityVenue;

    /**
     * 发布区域-学校ID
     */
    @NotNull
    @Schema(description = "发布区域-学校ID", example = "1")
    private Long schoolId;

    /**
     * 学校名称
     */
    @Schema(description = "学校名称", example = "清华大学")
    private String schoolName;
    
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
     * 点赞数
     * */
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 评论数
     */
    @Schema(description = "评论数")
    private Integer commentCount;

    /**
     * 分享数
     */
    @Schema(description = "分享数")
    private Integer shareCount;

    /**
     * 报名开始时间
     */
    @NotNull
    @Schema(description = "报名开始时间", example = "2023-09-01T00:00:00")
    private LocalDateTime registrationStartTime;

    /**
     * 报名截止时间
     */
    @NotNull
    @Schema(description = "报名截止时间", example = "2023-09-30T00:00:00")
    private LocalDateTime registrationEndTime;

    /**
     * 活动时间
     */
    @NotNull
    @Schema(description = "活动时间", example = "2023-10-15T00:00:00")
    private LocalDateTime activityTime;

    /**
     * 活动图片(JSON数组)
     */
    @Schema(description = "活动图片(JSON数组)", example = "[\"image1.jpg\", \"image2.jpg\"]")
    private List<String> images;

    /**
     * 是否显示(1-显示 0-隐藏)
     */
    @Schema(description = "是否显示(1-显示 0-隐藏)")
    private Integer isVisible;

    /**
     * 活动状态(0-草稿 1-待审核 2-已发布 3-已取消)
     */
    @Schema(description = "活动状态(0-草稿 1-待审核 2-已发布 3-已取消)")
    private Integer status;

    /**
     * 创建时间
     */
    @NotNull
    @Schema(description = "创建时间", example = "2023-08-01T00:00:00")
    private LocalDateTime createdAt;

    /**
     * 软删除时间
     */
    @Schema(description = "软删除时间", example = "2023-12-01T00:00:00")
    private LocalDateTime deletedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}