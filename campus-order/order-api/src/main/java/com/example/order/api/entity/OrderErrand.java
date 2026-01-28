package com.example.order.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "跑腿订单扩展表")
public class OrderErrand implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "主键ID", example = "1")
	private Long id;

	@Schema(description = "订单主表ID", example = "10001")
	private Long orderId;

	@Schema(description = "服务费", example = "10.00")
	private BigDecimal serviceFee;

	@Schema(description = "服务分类id", example = "1")
	private Long serviceTypeId;

	@Schema(description = "取货地址id", example = "2001")
	private String pickupAddressId;

	@Schema(description = "送货地址id", example = "2002")
	private String deliveryAddressId;

	@Schema(description = "物品描述", example = "笔记本电脑一台，包装完好")
	private String itemDescription;

	@Schema(description = "物品重量", example = "2.5")
	private BigDecimal itemWeight;

	@Schema(description = "长度", example = "35.0")
	private BigDecimal length;

	@Schema(description = "宽度", example = "25.0")
	private BigDecimal width;

	@Schema(description = "高度", example = "5.0")
	private BigDecimal height;

	@Schema(description = "体积(cm³，可计算得出)", example = "0.0044")
	private BigDecimal volume;

	@Schema(description = "服务人员ID", example = "5001")
	private Long staffId;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间", example = "2026-01-19T12:30:00")
	private LocalDateTime createdAt;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(description = "更新时间", example = "2026-01-19T12:58:00")
	private LocalDateTime updatedAt;
}
