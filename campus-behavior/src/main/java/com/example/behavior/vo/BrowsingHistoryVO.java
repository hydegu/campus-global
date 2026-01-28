package com.example.behavior.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 浏览记录返回对象VO
 * 用于返回浏览记录的详细信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BrowsingHistoryVO", description = "浏览记录返回对象VO")
public class BrowsingHistoryVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 浏览记录ID
     */
    @Schema(description = "浏览记录ID，自增主键", example = "1")
    private Long id;
    
    /**
     * 创建日期，用于分区
     */
    @Schema(description = "创建日期，浏览该内容的日期", example = "2026-01-28")
    private LocalDate createdDate;
    
    /**
     * 用户ID
     */
    @Schema(description = "用户ID，浏览该内容的用户", example = "10001")
    private Long userId;
    
    /**
     * 内容类型：1-论坛帖子，2-外卖商家
     */
    @Schema(description = "内容类型：1-论坛帖子，2-外卖商家", example = "1")
    private Integer contentType;
    
    /**
     * 内容类型描述
     */
    @Schema(description = "内容类型描述：论坛帖子或外卖商家", example = "论坛帖子")
    private String contentTypeDesc;
    
    /**
     * 内容ID（帖子ID或商家ID）
     */
    @Schema(description = "内容ID，当contentType为1时为帖子ID，当contentType为2时为商家ID", example = "1001")
    private Long contentId;
    
    /**
     * 内容标题（冗余）
     */
    @Schema(description = "内容标题，帖子的标题或商家的名称（冗余字段，用于快速展示）", 
            example = "校园美食节活动")
    private String contentTitle;
    
    /**
     * 内容图片（冗余）
     */
    @Schema(description = "内容图片URL，帖子的封面图或商家的logo（冗余字段，用于快速展示）", 
            example = "https://example.com/images/post123.jpg")
    private String contentImage;
    
    /**
     * 内容描述（冗余）
     */
    @Schema(description = "内容描述，帖子的摘要或商家的简介（冗余字段，用于快速展示）", 
            example = "校园美食节即将开始，欢迎各位同学参加")
    private String contentDescription;
}