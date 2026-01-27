package com.example.order.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "订单详情VO")
public class OrderDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "订单ID", example = "10001")
	private Long id;

	@Schema(description = "订单编号", example = "20260119120001ABCDEF")
	private String orderNo;

	@Schema(description = "订单类型:1-外卖 2-服务 3-其他", example = "1")
	private Integer orderType;

	@Schema(description = "订单状态:1-待支付 2-待接单 3-待取货 4-配送中 5-已送达 6-已取消 7-已完成 8-售后中", example = "4")
	private Integer orderStatus;

	@Schema(description = "订单总金额", example = "68.00")
	private BigDecimal totalAmount;

	@Schema(description = "实付金额", example = "68.00")
	private BigDecimal actualAmount;

	@Schema(description = "服务提供方名称", example = "张三快餐")
	private String serviceProviderName;

	@Schema(description = "预计送达时间", example = "2026-01-19T12:30:00")
	private LocalDateTime estimatedDeliveryTime;

	@Schema(description = "创建时间", example = "2026-01-19T12:00:00")
	private LocalDateTime createAt;

	@Schema(description = "用户姓名", example = "李四")
	private String userName;

	@Schema(description = "用户电话", example = "13800138000")
	private String userPhone;

	@Schema(description = "支付状态:0-待支付 1-已支付 2-部分退款 3-全额退款", example = "1")
	private Integer payStatus;

	@Schema(description = "支付方式:1-在线支付 2-微信 3-线下支付 4-其他", example = "2")
	private Integer payMethod;

	@Schema(description = "支付时间", example = "2026-01-19T12:05:00")
	private LocalDateTime payTime;

	@Schema(description = "配送费", example = "5.00")
	private BigDecimal deliveryFee;

	@Schema(description = "骑手姓名", example = "王五")
	private String riderName;

	@Schema(description = "骑手电话", example = "13900139000")
	private String riderPhone;

	@Schema(description = "实际送达时间", example = "2026-01-19T12:28:00")
	private LocalDateTime actualDeliveryTime;

	@Schema(description = "距离", example = "3.5")
	private BigDecimal distance;

	@Schema(description = "订单备注", example = "不要辣，多放葱")
	private String remark;
}
