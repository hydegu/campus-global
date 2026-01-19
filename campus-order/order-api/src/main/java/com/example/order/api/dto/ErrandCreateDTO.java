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
	@Schema(description = "服务分类ID")
	private Integer serviceTypeId;

	@NotNull(message = "取货地址ID不能为空")
	@Schema(description = "取货地址ID")
	private String pickupAddressId;

	@NotNull(message = "送货地址ID不能为空")
	@Schema(description = "送货地址ID")
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

	@NotNull(message = "配送费不能为空")
	@Schema(description = "配送费（由前端传递，不计算）")
	private BigDecimal deliveryFee;

	@Schema(description = "订单备注")
	private String remark;
}
