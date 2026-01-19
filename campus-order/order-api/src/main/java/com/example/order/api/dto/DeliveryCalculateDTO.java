package com.example.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "配送费计算DTO")
public class DeliveryCalculateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "商家ID不能为空")
	@Schema(description = "商家ID")
	private Long merchantId;

	@NotNull(message = "收货地址ID不能为空")
	@Schema(description = "收货地址ID")
	private Long deliveryAddressId;

	@NotNull(message = "商品金额不能为空")
	@Schema(description = "商品金额")
	private BigDecimal goodsAmount;
}
