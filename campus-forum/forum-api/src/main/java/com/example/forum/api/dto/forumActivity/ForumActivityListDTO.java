package com.example.forum.api.dto.forumActivity;

import com.example.forum.api.dto.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 论坛活动列表查询DTO
 */
@Data
public class ForumActivityListDTO extends PageQuery {

    /**
     * 发布人ID
     */
    @Schema(description = "发布人ID", example = "1")
    private Long publisherId;
}