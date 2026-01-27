package com.example.merchant.api.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "CategoryAddDTO", description = "新增商品分类请求DTO")
public class CategoryAddDTO {

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    @Schema(description = "父级ID（0为顶级分类）", example = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "父级ID不能为空")
    private Long parentId;

    @Schema(description = "分类层级：1-一级，2-二级，3-三级", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分类层级不能为空")
    @Min(value = 1, message = "分类层级必须在1-3之间")
    @Max(value = 3, message = "分类层级必须在1-3之间")
    private Integer level;

    @Schema(description = "排序（值越小越靠前）", example = "10")
    private Integer sortOrder;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    @Min(value = 0, message = "状态必须是0或1")
    @Max(value = 1, message = "状态必须是0或1")
    private Integer status;

    @Schema(description = "是否全局分类：0-全局分类，1-商家自定义分类", example = "1")
    @Min(value = 0, message = "是否全局分类必须是0或1")
    @Max(value = 1, message = "是否全局分类必须是0或1")
    private Integer isGlobal;

    @Schema(description = "商家ID（isGlobal=1时必填）", example = "1")
    private Long mchId;
}