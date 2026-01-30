package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 查询提现记录请求DTO
 */
@Data
@Schema(name = "FinanceWithdrawalQueryDTO", description = "查询提现记录请求DTO")
public class FinanceWithdrawalQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID", example = "1001")
    private Long userId;

    @Schema(description = "状态：0-待审核 1-审核通过 2-审核拒绝 3-打款中 4-打款成功 5-打款失败", example = "0")
    private Integer status;

    @Schema(description = "开始时间", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", example = "2024-01-31 23:59:59")
    private LocalDateTime endTime;

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页数量", example = "10")
    private Integer pageSize = 10;
}