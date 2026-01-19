package com.example.order.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "外卖订单扩展表")
public class OrderDelivery implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "主键ID")
	private Long id;

	@Schema(description = "订单主表ID")
	private Long orderId;

	@Schema(description = "商家ID")
	private Long merchantId;

	@Schema(description = "收货地址id")
	private Long deliveryAddressId;

	@Schema(description = "商品总金额")
	private BigDecimal goodsAmount;

	@Schema(description = "配送费")
	private BigDecimal deliveryFee;

	@Schema(description = "骑手ID")
	private Long riderId;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间")
	private LocalDateTime createdAt;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(description = "更新时间")
	private LocalDateTime updatedAt;
}
