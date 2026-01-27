package com.example.merchant.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ErrandCategoryQueryDTO", description = "服务分类查询条件DTO")
public class ErrandCategoryQueryDTO {

    @Schema(description = "分类ID", example = "1")
    private Long categoryId;

    @Schema(description = "父级ID", example = "0")
    private Long parentId;

    @Schema(description = "分类层级：1-一级分类，2-二级分类", example = "1")
    private Integer level;

    @Schema(description = "分类名称（模糊查询）", example = "跑腿")
    private String categoryName;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    private Integer status;

    @Schema(description = "是否允许线下交易：0-否，1-是", example = "1")
    private Integer allowOfflineTrade;

    @Schema(description = "页码", example = "1")
    private Integer pageNum;

    @Schema(description = "每页大小", example = "10")
    private Integer pageSize;

    @Schema(description = "排序字段", example = "sortOrder")
    private String sortField;

    @Schema(description = "排序方向（asc/desc）", example = "asc")
    private String sortOrder;
}