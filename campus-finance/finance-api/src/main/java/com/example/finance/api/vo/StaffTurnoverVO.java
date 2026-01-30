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
 * 服务人员流水VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "StaffTurnoverVO", description = "服务人员流水VO")
public class StaffTurnoverVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "服务人员ID", example = "1001")
    private Long staffId;

    @Schema(description = "服务人员姓名", example = "王五")
    private String staffName;

    @Schema(description = "服务人员电话", example = "13900139000")
    private String phone;

    @Schema(description = "服务人员头像", example = "https://example.com/avatar.png")
    private String avatar;

    @Schema(description = "总流水金额", example = "30000.00")
    private BigDecimal totalAmount;

    @Schema(description = "订单数量", example = "150")
    private Integer orderCount;

    @Schema(description = "收入金额", example = "30000.00")
    private BigDecimal incomeAmount;

    @Schema(description = "开始时间", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", example = "2024-01-31 23:59:59")
    private LocalDateTime endTime;
}