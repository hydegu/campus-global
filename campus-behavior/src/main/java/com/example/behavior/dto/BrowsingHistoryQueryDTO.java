package com.example.behavior.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 浏览记录查询条件DTO
 * 用于分页查询用户的浏览记录
 */
@Data
@Schema(name = "BrowsingHistoryQueryDTO", description = "浏览记录查询条件DTO")
public class BrowsingHistoryQueryDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 内容类型：1-论坛帖子，2-外卖商家
     */
    @Schema(description = "内容类型：1-论坛帖子，2-外卖商家（可选，不传则查询所有类型）", 
            example = "1")
    private Integer contentType;
    
    /**
     * 页码（从1开始）
     */
    @Schema(description = "页码，从1开始", 
            example = "1", 
            defaultValue = "1")
    private Integer pageNum;
    
    /**
     * 每页大小
     */
    @Schema(description = "每页大小", 
            example = "10", 
            defaultValue = "10")
    private Integer pageSize;
}