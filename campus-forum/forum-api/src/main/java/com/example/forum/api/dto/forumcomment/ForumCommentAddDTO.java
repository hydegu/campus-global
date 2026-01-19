package com.example.forum.api.dto.forumcomment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ForumCommentAddDTO {


    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    @Size(max = 1000, message = "评论内容不能超过1000个字符")
    @Schema(description = "评论内容", example = "评论内容")
    private String commentContent;

    /**
     * 父级评论ID(NULL表示根评论)
     */
    @Schema(description = "父级评论ID(NULL表示根评论)", example = "null")
    private Long parentId;

    /**
     * 根评论ID(方便查询整个评论树)
     */
    @Schema(description = "根评论ID(方便查询整个评论树)", example = "1")
    private Long rootId;

    /**
     * 评论层级(0-根评论 1-一级回复 2-二级回复)
     */
    @Schema(description = "评论层级(0-根评论 1-一级回复 2-二级回复)",example = "0")
    private Integer level;


}
