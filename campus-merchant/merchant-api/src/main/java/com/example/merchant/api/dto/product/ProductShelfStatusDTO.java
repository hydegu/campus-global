package com.example.merchant.api.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "ProductShelfStatusDTO", description = "商品上架状态变更DTO")
public class ProductShelfStatusDTO {

    @Schema(description = "上架状态：0-未上架，1-已上架", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上架状态不能为空")
    @Min(value = 0, message = "上架状态必须是0或1")
    @Max(value = 1, message = "上架状态必须是0或1")
    private Integer shelfStatus;
}