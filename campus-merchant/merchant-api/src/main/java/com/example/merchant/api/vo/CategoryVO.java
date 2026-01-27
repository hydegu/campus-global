package com.example.merchant.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CategoryVO", description = "商品分类返回对象VO")
public class CategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类ID", example = "1")
    private Long id;

    @Schema(description = "分类名称", example = "食品饮料")
    private String categoryName;

    @Schema(description = "父级ID", example = "0")
    private Long parentId;

    @Schema(description = "分类层级", example = "1")
    private Integer level;

    @Schema(description = "排序", example = "10")
    private Integer sortOrder;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    private Integer status;

    @Schema(description = "是否全局分类：0-全局分类，1-商家自定义分类", example = "1")
    private Integer isGlobal;

    @Schema(description = "商家ID", example = "1")
    private Long mchId;

    @Schema(description = "父分类名称", example = "食品")
    private String parentName;

    @Schema(description = "层级路径", example = "食品 > 饮料")
    private String levelPath;

    @Schema(description = "子分类列表")
    private List<CategoryVO> children;
}