package com.example.order.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "订单数统计响应")
public class OrderCountVO {

	@Schema(description = "总订单数", example = "150")
	private Long totalCount;

	@Schema(description = "待支付数", example = "5")
	private Long waitPayCount;

	@Schema(description = "待接单数", example = "3")
	private Long waitAcceptCount;

	@Schema(description = "待取货数", example = "2")
	private Long waitPickupCount;

	@Schema(description = "配送中数", example = "8")
	private Long deliveringCount;

	@Schema(description = "已送达数", example = "12")
	private Long deliveredCount;

	@Schema(description = "已取消数", example = "10")
	private Long cancelledCount;

	@Schema(description = "已完成数", example = "105")
	private Long completedCount;

	@Schema(description = "售后中数", example = "5")
	private Long afterSaleCount;
}
