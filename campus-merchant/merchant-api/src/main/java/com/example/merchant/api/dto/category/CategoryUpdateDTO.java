package com.example.merchant.api.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "CategoryUpdateDTO", description = "更新商品分类请求DTO")
public class CategoryUpdateDTO {

    @Schema(description = "分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分类ID不能为空")
    private Long id;

    @Schema(description = "分类名称", example = "食品饮料")
    private String categoryName;

    @Schema(description = "父级ID", example = "0")
    private Long parentId;

    @Schema(description = "分类层级", example = "1")
    @Min(value = 1, message = "分类层级必须在1-3之间")
    @Max(value = 3, message = "分类层级必须在1-3之间")
    private Integer level;

    @Schema(description = "排序", example = "10")
    private Integer sortOrder;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    @Min(value = 0, message = "状态必须是0或1")
    @Max(value = 1, message = "状态必须是0或1")
    private Integer status;
}