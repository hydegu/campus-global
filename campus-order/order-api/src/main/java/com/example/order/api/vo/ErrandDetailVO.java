package com.example.order.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "服务订单详情VO")
public class ErrandDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "订单ID")
	private Long id;

	@Schema(description = "订单编号")
	private String orderNo;

	@Schema(description = "订单类型:1-外卖 2-服务 3-其他")
	private Integer orderType;

	@Schema(description = "订单状态:1-待支付 2-待接单 3-待取货 4-配送中 5-已送达 6-已取消 7-已完成 8-售后中")
	private Integer orderStatus;

	@Schema(description = "订单总金额")
	private BigDecimal totalAmount;

	@Schema(description = "实付金额")
	private BigDecimal actualAmount;

	@Schema(description = "服务提供方名称")
	private String serviceProviderName;

	@Schema(description = "预计送达时间")
	private LocalDateTime estimatedDeliveryTime;

	@Schema(description = "创建时间")
	private LocalDateTime createAt;

	@Schema(description = "用户姓名")
	private String userName;

	@Schema(description = "用户电话")
	private String userPhone;

	@Schema(description = "支付状态:0-待支付 1-已支付 2-部分退款 3-全额退款")
	private Integer payStatus;

	@Schema(description = "支付方式:1-在线支付 2-微信 3-线下支付 4-其他")
	private Integer payMethod;

	@Schema(description = "支付时间")
	private LocalDateTime payTime;

	@Schema(description = "服务费")
	private BigDecimal serviceFee;

	@Schema(description = "配送费")
	private BigDecimal deliveryFee;

	@Schema(description = "服务分类名称")
	private String serviceTypeName;

	@Schema(description = "取货地址信息")
	private String pickupAddress;

	@Schema(description = "送货地址信息")
	private String deliveryAddress;

	@Schema(description = "物品描述")
	private String itemDescription;

	@Schema(description = "物品重量")
	private BigDecimal itemWeight;

	@Schema(description = "物品尺寸（长x宽x高）")
	private String itemSize;

	@Schema(description = "体积")
	private BigDecimal volume;

	@Schema(description = "服务人员姓名")
	private String staffName;

	@Schema(description = "服务人员电话")
	private String staffPhone;

	@Schema(description = "实际送达时间")
	private LocalDateTime actualDeliveryTime;

	@Schema(description = "距离")
	private BigDecimal distance;

	@Schema(description = "订单备注")
	private String remark;
}
