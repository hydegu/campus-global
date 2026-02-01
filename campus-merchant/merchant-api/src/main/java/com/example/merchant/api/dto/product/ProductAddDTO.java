package com.example.merchant.api.dto.product;

import com.example.merchant.api.dto.spec.SpecAddDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(name = "ProductAddDTO", description = "新增商品请求DTO")
public class ProductAddDTO {

    // ========== 基本信息 ==========
    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "商品名称不能为空")
    private String productName;

    @Schema(description = "商品标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "商品标题不能为空")
    private String title;

    @Schema(description = "商品卖点", example = "新鲜美味")
    private String sellingPoints;

    @Schema(description = "商品详情（HTML）", example = "<p>商品详情...</p>")
    private String description;

    @Schema(description = "商品主图URL", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "商品主图不能为空")
    private String mainImage;

    @Schema(description = "图片URL数组", example = "[\"url1\", \"url2\"]")
    private List<String> images;

    // ========== 关联分类 ==========
    @Schema(description = "商品分类ID列表（支持多分类）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "商品分类ID列表不能为空")
    private List<Long> categoryIds;

    // ========== 商家信息 ==========
    @Schema(description = "商家ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商家ID不能为空")
    private Long merchantId;

    // ========== 价格和收益 ==========
    @Schema(description = "价格（统一规格时使用）", example = "20.00")
    @DecimalMin(value = "0.00", message = "价格不能小于0")
    private BigDecimal price;

    @Schema(description = "收益类型：1-按比例，2-固定金额", example = "1")
    @Min(value = 1, message = "收益类型必须是1或2")
    @Max(value = 2, message = "收益类型必须是1或2")
    private Integer profitType;

    @Schema(description = "合伙人收益（按比例时为百分比，固定金额时为具体金额）", example = "10")
    @DecimalMin(value = "0.00", message = "合伙人收益不能小于0")
    private BigDecimal partnerProfit;

    @Schema(description = "服务商家收益（按比例时为百分比，固定金额时为具体金额）", example = "90")
    @DecimalMin(value = "0.00", message = "服务商家收益不能小于0")
    private BigDecimal merchantProfit;

    // ========== 规格类型 ==========
    @Schema(description = "规格类型：1-统一规格，2-多规格", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "规格类型不能为空")
    @Min(value = 1, message = "规格类型必须是1或2")
    @Max(value = 2, message = "规格类型必须是1或2")
    private Integer specType;

    // ========== 规格列表（specType=2时必填） ==========
    @Schema(description = "商品规格列表（specType=2时必填）")
    @Valid
    private List<SpecAddDTO> specs;

    // ========== 上架状态 ==========
    @Schema(description = "上架状态：0-未上架，1-已上架", example = "0")
    @Min(value = 0, message = "上架状态必须是0或1")
    @Max(value = 1, message = "上架状态必须是0或1")
    private Integer shelfStatus;

    // ========== 排序 ==========
    @Schema(description = "排序（数字越小越靠前）", example = "0")
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sortOrder;
}
