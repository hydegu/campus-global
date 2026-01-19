package com.example.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "订单数查询请求")
public class OrderCountQueryDTO {

	@Schema(description = "用户ID（可选，不传则从token获取）")
	private Long userId;

	@Schema(description = "订单类型：1-外卖 2-服务 3-其他（可选）")
	private Integer orderType;

	@Schema(description = "订单状态：1-待支付 2-待接单 3-待取货 4-配送中 5-已送达 6-已取消 7-已完成 8-售后中（可选）")
	private Integer orderStatus;

	@Schema(description = "开始日期（可选）")
	private LocalDateTime startDate;

	@Schema(description = "结束日期（可选）")
	private LocalDateTime endDate;
}
