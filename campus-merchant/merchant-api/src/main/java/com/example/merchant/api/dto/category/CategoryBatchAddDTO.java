package com.example.merchant.api.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "CategoryBatchAddDTO", description = "批量新增商品分类请求DTO")
public class CategoryBatchAddDTO {

    @Schema(description = "商品分类列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "商品分类列表不能为空")
    @Valid
    private List<CategoryAddDTO> categories;
}