package com.example.merchant.api.dto.spec;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "SpecBatchAddDTO", description = "批量新增商品规格请求DTO")
public class SpecBatchAddDTO {

    @Schema(description = "商品规格列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "商品规格列表不能为空")
    @Valid
    private List<SpecAddDTO> specs;
}