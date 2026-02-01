package com.example.merchant.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ProductVO", description = "商品返回对象VO")
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // ========== 基本信息 ==========
    @Schema(description = "商品ID", example = "1")
    private Long id;

    @Schema(description = "商品名称", example = "珍珠奶茶")
    private String productName;

    @Schema(description = "商品标题", example = "招牌珍珠奶茶")
    private String title;

    @Schema(description = "商品卖点", example = "新鲜美味")
    private String sellingPoints;

    @Schema(description = "商品详情（HTML）", example = "<p>商品详情...</p>")
    private String description;

    @Schema(description = "商品主图URL", example = "http://example.com/product.jpg")
    private String mainImage;

    @Schema(description = "图片URL数组", example = "[\"url1\", \"url2\"]")
    private List<String> images;

    // ========== 分类信息 ==========
    @Schema(description = "商品分类ID", example = "1")
    private Long categoryId;

    @Schema(description = "商品分类名称", example = "饮品")
    private String categoryName;

    @Schema(description = "商品分类ID列表", example = "[1, 2, 3]")
    private List<Long> categoryIds;

    @Schema(description = "商品分类名称列表", example = "[\"饮品\", \"奶茶\"]")
    private List<String> categoryNames;

    // ========== 商家信息 ==========
    @Schema(description = "商家ID", example = "1")
    private Long merchantId;

    @Schema(description = "商家名称", example = "奶茶店")
    private String merchantName;

    // ========== 价格和收益 ==========
    @Schema(description = "价格（统一规格时使用）", example = "20.00")
    private BigDecimal price;

    @Schema(description = "收益类型：1-按比例，2-固定金额", example = "1")
    private Integer profitType;

    @Schema(description = "合伙人收益", example = "10")
    private BigDecimal partnerProfit;

    @Schema(description = "服务商家收益", example = "90")
    private BigDecimal merchantProfit;

    // ========== 规格信息 ==========
    @Schema(description = "规格类型：1-统一规格，2-多规格", example = "2")
    private Integer specType;

    @Schema(description = "商品规格列表")
    private List<SpecVO> specs;

    // ========== 上架状态 ==========
    @Schema(description = "上架状态：0-未上架，1-已上架", example = "1")
    private Integer shelfStatus;

    @Schema(description = "审核记录ID", example = "1")
    private Long auditId;

    // ========== 排序 ==========
    @Schema(description = "排序（数字越小越靠前）", example = "0")
    private Integer sortOrder;
}