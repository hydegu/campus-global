package com.example.order.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "服务订单列表VO")
public class ErrandListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "订单ID", example = "10001")
	private Long id;

	@Schema(description = "订单编号", example = "20260119120001ABCDEF")
	private String orderNo;

	@Schema(description = "订单类型:1-外卖 2-服务 3-其他", example = "2")
	private Integer orderType;

	@Schema(description = "订单状态:1-待支付 2-待接单 3-待取货 4-配送中 5-已送达 6-已取消 7-已完成 8-售后中", example = "4")
	private Integer orderStatus;

	@Schema(description = "订单总金额", example = "25.00")
	private BigDecimal totalAmount;

	@Schema(description = "实付金额", example = "25.00")
	private BigDecimal actualAmount;

	@Schema(description = "服务提供方名称", example = "赵六")
	private String serviceProviderName;

	@Schema(description = "预计送达时间", example = "2026-01-19T13:00:00")
	private LocalDateTime estimatedDeliveryTime;

	@Schema(description = "创建时间", example = "2026-01-19T12:30:00")
	private LocalDateTime createAt;
}
