package com.example.finance.api.vo;

import com.example.finance.api.pojo.MonthlyTurnoverItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "交易流水统计VO")
@Data
public class TransactionLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "今日打款金额/元", example = "5000.00")
    private BigDecimal todayPaymentAmount;

    @Schema(description = "今日提现金额/元", example = "2000.00")
    private BigDecimal todayWithdrawalAmount;

    @Schema(description = "今日流水/元（总交易额）", example = "7000.00")
    private BigDecimal todayTurnover;

    @Schema(description = "今日成交金额/元", example = "6500.00")
    private BigDecimal todayOrderAmount;

    @Schema(description = "今日成交订单/单", example = "50")
    private Integer todayOrderCount;

    @Schema(description = "平台每月流水", example = "[{\"month\":\"2024-01\",\"amount\":\"100000.00\"}]")
    private List<MonthlyTurnoverItem> monthlyData;
}

