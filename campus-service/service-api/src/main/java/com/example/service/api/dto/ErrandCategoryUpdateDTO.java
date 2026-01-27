package com.example.service.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Schema(name = "ErrandCategoryUpdateDTO", description = "更新服务分类请求DTO")
public class ErrandCategoryUpdateDTO {

    @Schema(description = "主键ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "分类名称", example = "跑腿代购")
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    @Schema(description = "排序（值越小越靠前显示）", example = "10")
    private Integer sortOrder;

    @Schema(description = "是否允许线下交易：0-否，1-是", example = "1")
    @Min(value = 0, message = "是否允许线下交易必须是0或1")
    @Max(value = 1, message = "是否允许线下交易必须是0或1")
    private Integer allowOfflineTrade;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    @Min(value = 0, message = "状态必须是0或1")
    @Max(value = 1, message = "状态必须是0或1")
    private Integer status;

    @Schema(description = "服务分佣比例（0-100），10代表10%", example = "15")
    @Min(value = 0, message = "分佣比例不能小于0")
    @Max(value = 100, message = "分佣比例不能大于100")
    private Integer commissionRate;
}