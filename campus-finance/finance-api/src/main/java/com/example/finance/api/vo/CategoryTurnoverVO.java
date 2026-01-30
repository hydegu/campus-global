package com.example.finance.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 品类流水VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CategoryTurnoverVO", description = "品类流水VO")
public class CategoryTurnoverVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "品类ID", example = "10")
    private Long categoryId;

    @Schema(description = "品类名称", example = "奶茶饮品")
    private String categoryName;

    @Schema(description = "总流水金额", example = "500000.00")
    private BigDecimal totalAmount;

    @Schema(description = "订单数量", example = "2500")
    private Integer orderCount;

    @Schema(description = "商家数量", example = "50")
    private Integer merchantCount;

    @Schema(description = "开始时间", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", example = "2024-01-31 23:59:59")
    private LocalDateTime endTime;
}