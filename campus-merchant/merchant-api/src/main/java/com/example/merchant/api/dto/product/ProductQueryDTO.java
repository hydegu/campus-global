package com.example.merchant.api.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "ProductQueryDTO", description = "商品查询条件DTO")
public class ProductQueryDTO {

    @Schema(description = "商品ID", example = "1")
    private Long productId;

    @Schema(description = "商品名称（模糊查询）", example = "奶茶")
    private String productName;

    @Schema(description = "商品标题（模糊查询）", example = "珍珠奶茶")
    private String title;

    @Schema(description = "商品分类ID", example = "1")
    private Long categoryId;

    @Schema(description = "商家ID", example = "1")
    private Long merchantId;

    @Schema(description = "最小价格", example = "10.00")
    @DecimalMin(value = "0.00", message = "最小价格不能小于0")
    private BigDecimal minPrice;

    @Schema(description = "最大价格", example = "100.00")
    @DecimalMin(value = "0.00", message = "最大价格不能小于0")
    private BigDecimal maxPrice;

    @Schema(description = "规格类型：1-统一规格，2-多规格", example = "1")
    private Integer specType;

    @Schema(description = "上架状态：0-未上架，1-已上架", example = "1")
    private Integer shelfStatus;

    @Schema(description = "页码", example = "1")
    private Integer pageNum;

    @Schema(description = "每页大小", example = "10")
    private Integer pageSize;

    @Schema(description = "排序字段", example = "price")
    private String sortField;

    @Schema(description = "排序方向（asc/desc）", example = "asc")
    private String sortOrder;
}