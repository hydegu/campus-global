package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商家流水查询请求DTO
 */
@Data
@Schema(name = "MerchantTurnoverQueryDTO", description = "商家流水查询请求DTO")
public class MerchantTurnoverQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商家ID", example = "1001")
    private Long merchantId;

    @Schema(description = "品类ID（仅全局分类）", example = "10")
    private Long categoryId;

    @Schema(description = "开始时间", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", example = "2024-01-31 23:59:59")
    private LocalDateTime endTime;

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页数量", example = "10")
    private Integer pageSize = 10;
}