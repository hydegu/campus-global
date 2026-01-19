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
	@Schema(description = "商家ID", example = "1001")
	private Long merchantId;

	@NotNull(message = "收货地址ID不能为空")
	@Schema(description = "收货地址ID", example = "2001")
	private Long deliveryAddressId;

	@Schema(description = "商品列表", example = "[{\"productId\":3001,\"quantity\":2,\"price\":25.50,\"specId\":4001},{\"productId\":3002,\"quantity\":1,\"price\":18.00,\"specId\":null}]")
	private List<OrderGoodsItem> goodsList;

	@Schema(description = "订单备注", example = "不要辣，多放葱")
	private String remark;

	@Data
	@Schema(description = "订单商品项")
	public static class OrderGoodsItem implements Serializable {

		private static final long serialVersionUID = 1L;

		@NotNull(message = "商品ID不能为空")
		@Schema(description = "商品ID", example = "3001")
		private Long productId;

		@NotNull(message = "商品数量不能为空")
		@Schema(description = "商品数量", example = "2")
		private Integer quantity;

		@NotNull(message = "商品单价不能为空")
		@Schema(description = "商品单价", example = "25.50")
		private BigDecimal price;

		@Schema(description = "规格ID", example = "4001")
		private Long specId;
	}
}
