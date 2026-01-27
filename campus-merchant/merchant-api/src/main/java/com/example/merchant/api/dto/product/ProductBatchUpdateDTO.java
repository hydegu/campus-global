package com.example.merchant.api.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "ProductBatchUpdateDTO", description = "批量更新商品请求DTO")
public class ProductBatchUpdateDTO {

    @Schema(description = "商品列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "商品列表不能为空")
    @Valid
    private List<ProductUpdateDTO> products;
}