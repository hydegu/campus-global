package com.example.merchant.api.dto.spec;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "SpecUpdateDTO", description = "更新商品规格请求DTO")
public class SpecUpdateDTO {

    @Schema(description = "规格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "规格ID不能为空")
    private Long id;

    @Schema(description = "规格组名", example = "口味")
    private String groupName;

    @Schema(description = "规格名称", example = "大份")
    private String specName;

    @Schema(description = "价格", example = "25.00")
    @DecimalMin(value = "0.00", message = "价格不能小于0")
    private BigDecimal price;

    @Schema(description = "规格图片URL", example = "http://example.com/spec.jpg")
    private String specImage;

    @Schema(description = "状态：0-下架，1-上架", example = "1")
    @Min(value = 0, message = "状态必须是0或1")
    @Max(value = 1, message = "状态必须是0或1")
    private Integer status;

    @Schema(description = "是否默认规格：0-否，1-是", example = "0")
    @Min(value = 0, message = "是否默认规格必须是0或1")
    @Max(value = 1, message = "是否默认规格必须是0或1")
    private Integer isDefault;

    @Schema(description = "排序", example = "10")
    private Integer sortOrder;
}