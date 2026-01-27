package com.example.service.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Schema(name = "ErrandCategoryBatchUpdateDTO", description = "批量更新服务分类请求DTO")
public class ErrandCategoryBatchUpdateDTO {

    @Schema(description = "服务分类列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "服务分类列表不能为空")
    @Valid
    private List<ErrandCategoryUpdateDTO> categories;
}