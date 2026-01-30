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
 * 商家流水VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MerchantTurnoverVO", description = "商家流水VO")
public class MerchantTurnoverVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商家ID", example = "1001")
    private Long merchantId;

    @Schema(description = "商家名称", example = "张三餐厅")
    private String merchantName;

    @Schema(description = "商家logo", example = "https://example.com/logo.png")
    private String logo;

    @Schema(description = "联系人姓名", example = "张三")
    private String contactName;

    @Schema(description = "合伙人名称", example = "李四")
    private String partnerName;

    @Schema(description = "总流水金额", example = "100000.00")
    private BigDecimal totalAmount;

    @Schema(description = "订单数量", example = "500")
    private Integer orderCount;

    @Schema(description = "收入金额", example = "80000.00")
    private BigDecimal incomeAmount;

    @Schema(description = "提现金额", example = "20000.00")
    private BigDecimal withdrawAmount;

    @Schema(description = "打款金额", example = "75000.00")
    private BigDecimal paymentAmount;

    @Schema(description = "开始时间", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", example = "2024-01-31 23:59:59")
    private LocalDateTime endTime;
}