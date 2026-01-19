package com.example.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "骑手取货DTO")
public class OrderPickupDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "订单ID不能为空")
	@Schema(description = "订单ID")
	private Long orderId;
}
