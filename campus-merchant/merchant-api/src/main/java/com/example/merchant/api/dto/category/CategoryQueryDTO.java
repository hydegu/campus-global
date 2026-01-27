package com.example.merchant.api.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CategoryQueryDTO", description = "商品分类查询条件DTO")
public class CategoryQueryDTO {

    @Schema(description = "分类ID", example = "1")
    private Long categoryId;

    @Schema(description = "分类名称（模糊查询）", example = "食品")
    private String categoryName;

    @Schema(description = "父级ID", example = "0")
    private Long parentId;

    @Schema(description = "分类层级", example = "1")
    private Integer level;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    private Integer status;

    @Schema(description = "是否全局分类：0-全局分类，1-商家自定义分类", example = "1")
    private Integer isGlobal;

    @Schema(description = "商家ID", example = "1")
    private Long mchId;

    @Schema(description = "页码", example = "1")
    private Integer pageNum;

    @Schema(description = "每页大小", example = "10")
    private Integer pageSize;

    @Schema(description = "排序字段", example = "sortOrder")
    private String sortField;

    @Schema(description = "排序方向（asc/desc）", example = "asc")
    private String sortOrder;
}