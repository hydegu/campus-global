package com.example.service.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Data
@Schema(name = "CommissionConfigAddDTO", description = "新增分佣配置请求DTO")
public class CommissionConfigAddDTO {

    @Schema(description = "服务分类ID（configType=1-全局默认时可为null）", example = "1")
    private Long categoryId;

    @Schema(description = "配置类型：1-全局默认，2-服务分佣，3-商家分佣，4-合伙人分佣", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "配置类型不能为空")
    @Min(value = 1, message = "配置类型必须是1-4")
    @Max(value = 4, message = "配置类型必须是1-4")
    private Integer configType;

    @Schema(description = "分佣比例（0-100），10代表10%", example = "15", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分佣比例不能为空")
    @Min(value = 0, message = "分佣比例不能小于0")
    @Max(value = 100, message = "分佣比例不能大于100")
    private BigDecimal commissionRate;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    @Min(value = 0, message = "状态必须是0或1")
    @Max(value = 1, message = "状态必须是0或1")
    private Integer status;
}