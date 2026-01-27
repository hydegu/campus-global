package com.example.merchant.api.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "ProductBatchAddDTO", description = "批量新增商品请求DTO")
public class ProductBatchAddDTO {

    @Schema(description = "商品列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "商品列表不能为空")
    @Valid
    private List<ProductAddDTO> products;
}