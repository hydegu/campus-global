package com.example.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "订单查询DTO")
public class OrderQueryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "查询类型:1-用户查询自己的订单 2-服务者/骑手查询自己接的单 3-商家查询自己店铺的订单", example = "1")
	private Integer queryType;

	@Schema(description = "服务提供方类型:1-商家 2-服务人员/骑手（可选，配合queryType使用）", example = "2")
	private Integer serviceProviderType;

	@Schema(description = "服务提供方ID（可选，不传则从token获取）", example = "5001")
	private Long serviceProviderId;

	@Schema(description = "订单编号", example = "20260119120001ABCDEF")
	private String orderNo;

	@Schema(description = "订单状态:1-待支付 2-待接单 3-待取货 4-配送中 5-已送达 6-已取消 7-已完成 8-售后中", example = "4")
	private Integer orderStatus;

	@Schema(description = "订单类型:1-外卖 2-服务 3-其他", example = "1")
	private Integer orderType;

	@Schema(description = "开始时间", example = "2026-01-01T00:00:00")
	private LocalDateTime startTime;

	@Schema(description = "结束时间", example = "2026-01-31T23:59:59")
	private LocalDateTime endTime;

	@Schema(description = "页码", example = "1")
	private Integer pageNum = 1;

	@Schema(description = "每页数量", example = "10")
	private Integer pageSize = 10;
}
