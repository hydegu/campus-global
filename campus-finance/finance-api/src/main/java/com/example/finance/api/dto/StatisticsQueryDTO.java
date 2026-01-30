package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 统计查询请求DTO
 */
@Data
@Schema(name = "StatisticsQueryDTO", description = "统计查询请求DTO")
public class StatisticsQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "开始日期", example = "2024-01-01")
    private LocalDate startDate;

    @Schema(description = "结束日期", example = "2024-01-31")
    private LocalDate endDate;
}