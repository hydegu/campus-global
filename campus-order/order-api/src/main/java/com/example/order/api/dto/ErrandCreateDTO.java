package com.example.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "服务订单创建DTO")
public class ErrandCreateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "服务分类ID不能为空")
	@Schema(description = "服务分类ID", example = "1")
	private Long serviceTypeId;

	@NotNull(message = "取货地址ID不能为空")
	@Schema(description = "取货地址ID", example = "2001")
	private String pickupAddressId;

	@NotNull(message = "送货地址ID不能为空")
	@Schema(description = "送货地址ID", example = "2002")
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

	@NotNull(message = "配送费不能为空")
	@Schema(description = "配送费（由前端传递，不计算）", example = "15.00")
	private BigDecimal deliveryFee;

	@Schema(description = "订单备注", example = "轻拿轻放，贵重物品")
	private String remark;
}
