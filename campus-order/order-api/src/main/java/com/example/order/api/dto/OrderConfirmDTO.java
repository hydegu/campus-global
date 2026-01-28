package com.example.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "外卖订单确认收货DTO")
public class OrderConfirmDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "订单ID不能为空")
	@Schema(description = "订单ID", example = "10001")
	private Long orderId;
}