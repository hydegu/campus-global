package com.example.merchant.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SpecVO", description = "商品规格返回对象VO")
public class SpecVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "规格ID", example = "1")
    private Long id;

    @Schema(description = "规格组名", example = "口味")
    private String groupName;

    @Schema(description = "规格名称", example = "大份")
    private String specName;

    @Schema(description = "价格", example = "25.00")
    private BigDecimal price;

    @Schema(description = "规格图片URL", example = "http://example.com/spec.jpg")
    private String specImage;

    @Schema(description = "状态：0-下架，1-上架", example = "1")
    private Integer status;

    @Schema(description = "是否默认规格：0-否，1-是", example = "0")
    private Integer isDefault;

    @Schema(description = "排序", example = "10")
    private Integer sortOrder;
}