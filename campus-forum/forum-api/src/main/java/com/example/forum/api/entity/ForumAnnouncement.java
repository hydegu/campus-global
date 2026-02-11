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
 * 公告表
 * @TableName forum_announcement
 */
@Schema(description = "公告表")
@TableName(value ="forum_announcement")
@Data
public class ForumAnnouncement implements Serializable {
    /**
     * 公告ID
     */
    @Schema(description = "公告ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 公告标题
     */
    @Schema(description = "公告标题")
    private String title;

    /**
     * 图片URL
     */
    @Schema(description = "图片URL")
    private String image;

    /**
     * 是否显示:0-不显示,1-显示
     */
    @Schema(description = "是否显示:0-不显示,1-显示")
    private Integer isDisplay;

    /**
     * 公告关联学校ID，null为系统公告
     */
    @Schema(description = "公告关联学校ID，null为系统公告")
    private Long schoolId;

    /**
     * 添加日期
     */
    @Schema(description = "添加日期")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    @Schema(description = "删除时间")
    private LocalDateTime deletedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}