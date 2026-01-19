package com.example.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "订单创建DTO")
public class OrderCreateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "商家ID不能为空")
	@Schema(description = "商家ID")
	private Long merchantId;

	@NotNull(message = "收货地址ID不能为空")
	@Schema(description = "收货地址ID")
	private Long deliveryAddressId;

	@Schema(description = "商品列表")
	private List<OrderGoodsItem> goodsList;

	@Schema(description = "订单备注")
	private String remark;

	@Data
	@Schema(description = "订单商品项")
	public static class OrderGoodsItem implements Serializable {

		private static final long serialVersionUID = 1L;

		@NotNull(message = "商品ID不能为空")
		@Schema(description = "商品ID")
		private Long productId;

		@NotNull(message = "商品数量不能为空")
		@Schema(description = "商品数量")
		private Integer quantity;

		@NotNull(message = "商品单价不能为空")
		@Schema(description = "商品单价")
		private BigDecimal price;

		@Schema(description = "规格ID")
		private Long specId;
	}
}
