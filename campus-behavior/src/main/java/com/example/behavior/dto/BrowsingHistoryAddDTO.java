package com.example.behavior.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加浏览记录请求DTO
 * 用于用户添加单条浏览记录
 */
@Data
@Schema(name = "BrowsingHistoryAddDTO", description = "添加浏览记录请求DTO")
public class BrowsingHistoryAddDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 内容类型：1-论坛帖子，2-外卖商家
     */
    @Schema(description = "内容类型：1-论坛帖子，2-外卖商家", 
            example = "1", 
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "内容类型不能为空")
    private Integer contentType;
    
    /**
     * 内容ID（帖子ID或商家ID）
     */
    @Schema(description = "内容ID，当contentType为1时为帖子ID，当contentType为2时为商家ID", 
            example = "1001", 
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "内容ID不能为空")
    private Long contentId;
}