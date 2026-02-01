package com.example.service.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CommissionConfigVO", description = "分佣配置返回对象VO")
public class CommissionConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "服务分类ID", example = "1")
    private Long categoryId;

    @Schema(description = "配置类型：1-全局默认，2-服务分佣，3-商家分佣，4-合伙人分佣", example = "2")
    private Integer configType;

    @Schema(description = "分佣比例（0-100），10代表10%", example = "15")
    private BigDecimal commissionRate;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    private Integer status;

    @Schema(description = "创建时间", example = "2026-01-27T10:00:00")
    private LocalDateTime createAt;

    @Schema(description = "更新时间", example = "2026-01-27T10:00:00")
    private LocalDateTime updateAt;

    @Schema(description = "服务分类名称", example = "跑腿代购")
    private String categoryName;

    @Schema(description = "配置类型名称", example = "服务分佣")
    private String configTypeName;
}