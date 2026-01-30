package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 查询打款记录请求DTO
 */
@Data
@Schema(name = "PaymentRecordQueryDTO", description = "查询打款记录请求DTO")
public class PaymentRecordQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "打款目标ID", example = "1001")
    private Long targetId;

    @Schema(description = "打款目标类型：1-商家 2-骑手 3-合伙人", example = "1")
    private Integer targetType;

    @Schema(description = "状态：0-待确认 1-待打款 2-已打款 3-打款失败", example = "0")
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