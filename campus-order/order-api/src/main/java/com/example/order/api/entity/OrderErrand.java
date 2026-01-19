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
	@Schema(description = "主键ID")
	private Long id;

	@Schema(description = "订单主表ID")
	private Long orderId;

	@Schema(description = "服务费")
	private BigDecimal serviceFee;

	@Schema(description = "服务分类id")
	private Integer serviceTypeId;

	@Schema(description = "取货地址id")
	private String pickupAddressId;

	@Schema(description = "送货地址id")
	private String deliveryAddressId;

	@Schema(description = "物品描述")
	private String itemDescription;

	@Schema(description = "物品重量")
	private BigDecimal itemWeight;

	@Schema(description = "长度")
	private BigDecimal length;

	@Schema(description = "宽度")
	private BigDecimal width;

	@Schema(description = "高度")
	private BigDecimal height;

	@Schema(description = "体积(cm³，可计算得出)")
	private BigDecimal volume;

	@Schema(description = "服务人员ID")
	private Long staffId;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间")
	private LocalDateTime createdAt;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(description = "更新时间")
	private LocalDateTime updatedAt;
}
