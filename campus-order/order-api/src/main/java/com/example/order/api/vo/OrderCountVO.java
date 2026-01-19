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

	@Schema(description = "总订单数")
	private Long totalCount;

	@Schema(description = "待支付数")
	private Long waitPayCount;

	@Schema(description = "待接单数")
	private Long waitAcceptCount;

	@Schema(description = "待取货数")
	private Long waitPickupCount;

	@Schema(description = "配送中数")
	private Long deliveringCount;

	@Schema(description = "已送达数")
	private Long deliveredCount;

	@Schema(description = "已取消数")
	private Long cancelledCount;

	@Schema(description = "已完成数")
	private Long completedCount;

	@Schema(description = "售后中数")
	private Long afterSaleCount;
}
