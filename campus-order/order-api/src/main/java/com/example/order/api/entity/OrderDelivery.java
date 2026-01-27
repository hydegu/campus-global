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
	@Schema(description = "主键ID", example = "1")
	private Long id;

	@Schema(description = "订单主表ID", example = "10001")
	private Long orderId;

	@Schema(description = "商家ID", example = "1001")
	private Long merchantId;

	@Schema(description = "收货地址id", example = "2001")
	private Long deliveryAddressId;

	@Schema(description = "商品总金额", example = "63.00")
	private BigDecimal goodsAmount;

	@Schema(description = "配送费", example = "5.00")
	private BigDecimal deliveryFee;

	@Schema(description = "骑手ID", example = "5001")
	private Long riderId;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间", example = "2026-01-19T12:00:00")
	private LocalDateTime createdAt;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(description = "更新时间", example = "2026-01-19T12:28:00")
	private LocalDateTime updatedAt;
}
