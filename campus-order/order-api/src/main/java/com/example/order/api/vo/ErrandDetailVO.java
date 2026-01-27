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

	@Schema(description = "订单ID", example = "10001")
	private Long id;

	@Schema(description = "订单编号", example = "20260119120001ABCDEF")
	private String orderNo;

	@Schema(description = "订单类型:1-外卖 2-服务 3-其他", example = "2")
	private Integer orderType;

	@Schema(description = "订单状态:1-待支付 2-待接单 3-待取货 4-配送中 5-已送达 6-已取消 7-已完成 8-售后中", example = "4")
	private Integer orderStatus;

	@Schema(description = "订单总金额", example = "25.00")
	private BigDecimal totalAmount;

	@Schema(description = "实付金额", example = "25.00")
	private BigDecimal actualAmount;

	@Schema(description = "服务提供方名称", example = "赵六")
	private String serviceProviderName;

	@Schema(description = "预计送达时间", example = "2026-01-19T13:00:00")
	private LocalDateTime estimatedDeliveryTime;

	@Schema(description = "创建时间", example = "2026-01-19T12:30:00")
	private LocalDateTime createAt;

	@Schema(description = "用户姓名", example = "李四")
	private String userName;

	@Schema(description = "用户电话", example = "13800138000")
	private String userPhone;

	@Schema(description = "支付状态:0-待支付 1-已支付 2-部分退款 3-全额退款", example = "1")
	private Integer payStatus;

	@Schema(description = "支付方式:1-在线支付 2-微信 3-线下支付 4-其他", example = "2")
	private Integer payMethod;

	@Schema(description = "支付时间", example = "2026-01-19T12:32:00")
	private LocalDateTime payTime;

	@Schema(description = "服务费", example = "10.00")
	private BigDecimal serviceFee;

	@Schema(description = "配送费", example = "15.00")
	private BigDecimal deliveryFee;

	@Schema(description = "服务分类名称", example = "文件配送")
	private String serviceTypeName;

	@Schema(description = "取货地址信息", example = "北京市朝阳区建国路88号")
	private String pickupAddress;

	@Schema(description = "送货地址信息", example = "北京市海淀区中关村大街1号")
	private String deliveryAddress;

	@Schema(description = "物品描述", example = "笔记本电脑一台，包装完好")
	private String itemDescription;

	@Schema(description = "物品重量", example = "2.5")
	private BigDecimal itemWeight;

	@Schema(description = "物品尺寸（长x宽x高）", example = "35.0x25.0x5.0")
	private String itemSize;

	@Schema(description = "体积", example = "0.0044")
	private BigDecimal volume;

	@Schema(description = "服务人员姓名", example = "赵六")
	private String staffName;

	@Schema(description = "服务人员电话", example = "13700137000")
	private String staffPhone;

	@Schema(description = "实际送达时间", example = "2026-01-19T12:58:00")
	private LocalDateTime actualDeliveryTime;

	@Schema(description = "距离", example = "5.2")
	private BigDecimal distance;

	@Schema(description = "订单备注", example = "轻拿轻放，贵重物品")
	private String remark;
}
