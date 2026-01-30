package com.example.finance.api.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 单月流水数据项
 */
@Data
public class MonthlyTurnoverItem {

    /**
     * 月份（格式：2025.1 或 2025-01）
     */
    private String month;

    /**
     * 流水金额/元
     */
    private BigDecimal turnover;
}
