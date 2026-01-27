package com.example.merchant.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Schema(name = "ErrandCategoryBatchAddDTO", description = "批量新增服务分类请求DTO")
public class ErrandCategoryBatchAddDTO {

    @Schema(description = "服务分类列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "服务分类列表不能为空")
    @Valid
    private List<ErrandCategoryAddDTO> categories;
}