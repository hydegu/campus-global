package com.example.forum.api.dto.forumActivity;

import com.example.forum.api.dto.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 论坛活动详情DTO
 */
@Data
public class ForumActivityDetailDTO extends PageQuery {
    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空")
    @Schema(description = "活动ID", example = "1")
    private Long id;
    
    /**
     * 用户姓名（模糊查询）
     */
    @Schema(description = "用户姓名（模糊查询）", example = "张三")
    private String userName;
    
    /**
     * 联系电话（模糊查询）
     */
    @Schema(description = "联系电话（模糊查询）", example = "13800138000")
    private String contactPhone;
}