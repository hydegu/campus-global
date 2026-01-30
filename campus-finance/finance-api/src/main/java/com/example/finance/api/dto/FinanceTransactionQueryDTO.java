package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 查询流水请求DTO
 */
@Data
@Schema(name = "FinanceTransactionQueryDTO", description = "查询流水请求DTO")
public class FinanceTransactionQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID", example = "1001")
    private Long userId;

    @Schema(description = "交易类型：1-打款 2-消费 3-退款 4-提现", example = "2")
    private Integer transactionType;

    @Schema(description = "关联业务类型：1-订单 2-提现 3-退款 4-打款", example = "1")
    private Integer relatedType;

    @Schema(description = "开始时间", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", example = "2024-01-31 23:59:59")
    private LocalDateTime endTime;

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页数量", example = "10")
    private Integer pageSize = 10;
}