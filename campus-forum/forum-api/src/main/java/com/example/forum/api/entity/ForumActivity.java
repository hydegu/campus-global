package com.example.forum.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动表
 * @TableName forum_activity
 */
@Schema(description = "活动表")
@TableName(value ="forum_activity")
@Data
public class ForumActivity implements Serializable {
    /**
     * 活动ID
     */
    @Schema(description = "活动ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活动标题
     */
    @Schema(description = "活动标题")
    private String activityTitle;

    /**
     * 活动内容
     */
    @Schema(description = "活动内容")
    private String activityContent;

    /**
     * 活动场地
     */
    @Schema(description = "活动场地")
    private String activityVenue;

    /**
     * 发布区域-学校ID
     */
    @Schema(description = "发布区域-学校ID")
    private Long schoolId;

    /**
     * 最大报名人数
     */
    @Schema(description = "最大报名人数")
    private Integer maxParticipants;

    /**
     * 当前报名人数
     */
    @Schema(description = "当前报名人数")
    private Integer currentParticipants;

    /**
     * 点赞数
     * */
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 浏览数
     * */
    @Schema(description = "分享数")
    private Integer shareCount;

    /**
     * 评论数
     * */
    @Schema(description = "评论数")
    private Integer commentCount;

    /**
     * 报名开始时间
     */
    @Schema(description = "报名开始时间")
    private LocalDateTime registrationStartTime;

    /**
     * 报名截止时间
     */
    @Schema(description = "报名截止时间")
    private LocalDateTime registrationEndTime;

    /**
     * 活动时间
     */
    @Schema(description = "活动时间")
    private LocalDateTime activityTime;

    /**
     * 活动图片(JSON数组)
     */
    @Schema(description = "活动图片(JSON数组)")
    private String images;

    /**
     * 审批表关联ID
     */
    @Schema(description = "审批表关联ID")
    private Long auditId;

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
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    /**
     * 软删除时间
     */
    @Schema(description = "软删除时间")
    private LocalDateTime deletedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}