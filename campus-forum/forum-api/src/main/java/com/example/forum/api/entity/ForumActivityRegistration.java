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
 * 活动报名表
 * @TableName forum_activity_registration
 */
@Schema(description = "活动报名表")
@TableName(value ="forum_activity_registration")
@Data
public class ForumActivityRegistration implements Serializable {
    /**
     * 报名ID
     */
    @Schema(description = "报名ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活动ID
     */
    @Schema(description = "活动ID")
    private Long activityId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 报名状态(1-已报名 2-已取消 3-已签到)
     */
    @Schema(description = "报名状态(1-已报名 2-已取消 3-已签到)")
    private Integer status;

    /**
     * 审核结果(0-待审核 1-已通过 2-已拒绝)
     */
    @Schema(description = "审核结果(0-待审核 1-已通过 2-已拒绝)")
    private Integer auditResult;

    /**
     * 报名时间
     */
    @Schema(description = "报名时间")
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateAt;

    /**
     * 软删除时间
     */
    @Schema(description = "软删除时间")
    private LocalDateTime deleteAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}