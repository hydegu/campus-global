package com.example.order.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "订单主表")
public class OrderMain implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "订单ID", example = "10001")
	private Long id;

	@Schema(description = "订单编号", example = "20260119120001ABCDEF")
	private String orderNo;

	@Schema(description = "订单类型:1-外卖 2-服务 3-其他", example = "1")
	private Integer orderType;

	@Schema(description = "下单用户ID", example = "1001")
	private Long userId;

	@Schema(description = "用户姓名", example = "李四")
	private String userName;

	@Schema(description = "用户电话", example = "13800138000")
	private String userPhone;

	@Schema(description = "订单总金额", example = "68.00")
	private BigDecimal totalAmount;

	@Schema(description = "实付金额", example = "68.00")
	private BigDecimal actualAmount;

	@Schema(description = "支付状态:0-待支付 1-已支付 2-部分退款 3-全额退款", example = "1")
	private Integer payStatus;

	@Schema(description = "支付方式:1-在线支付 2-微信 3-线下支付 4-其他", example = "2")
	private Integer payMethod;

	@Schema(description = "支付时间", example = "2026-01-19T12:05:00")
	private LocalDateTime payTime;

	@Schema(description = "第三方支付流水号", example = "WX2026011912050012345678")
	private String payChannelNo;

	@Schema(description = "订单状态:1-待支付 2-待接单 3-待取货 4-配送中 5-已送达 6-已取消 7-已完成 8-售后中", example = "4")
	private Integer orderStatus;

	@Schema(description = "取消类型:1-用户取消 2-商家取消 3-骑手取消 4-超时取消", example = "1")
	private Integer cancelType;

	@Schema(description = "取消时间", example = "2026-01-19T12:10:00")
	private LocalDateTime cancelTime;

	@Schema(description = "服务提供方类型:1-商家 2-服务人员", example = "1")
	private Integer serviceProviderType;

	@Schema(description = "服务提供方ID", example = "5001")
	private Long serviceProviderId;

	@Schema(description = "服务提供方名称", example = "张三快餐")
	private String serviceProviderName;

	@Schema(description = "学校/社区ID", example = "1")
	private Long schoolId;

	@Schema(description = "合伙人ID", example = "100")
	private Long partnerId;

	@Schema(description = "服务提供方预计收益", example = "60.00")
	private BigDecimal estimatedProviderIncome;

	@Schema(description = "合伙人预计收益", example = "5.00")
	private BigDecimal estimatedPartnerIncome;

	@Schema(description = "平台预计收益", example = "3.00")
	private BigDecimal estimatedPlatformIncome;

	@Schema(description = "乐观锁版本号", example = "1")
	private Integer version;

	@Schema(description = "订单备注", example = "不要辣，多放葱")
	private String remark;

	@Schema(description = "预计开始时间", example = "2026-01-19T12:10:00")
	private LocalDateTime estimatedStartTime;

	@Schema(description = "预计送达时间", example = "2026-01-19T12:30:00")
	private LocalDateTime estimatedDeliveryTime;

	@Schema(description = "实际送达时间", example = "2026-01-19T12:28:00")
	private LocalDateTime actualDeliveryTime;

	@Schema(description = "距离", example = "3.5")
	private BigDecimal distance;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间", example = "2026-01-19T12:00:00")
	private LocalDateTime createAt;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(description = "更新时间", example = "2026-01-19T12:28:00")
	private LocalDateTime updateAt;

	@TableLogic
	@Schema(description = "软删除时间", example = "2026-01-19T12:30:00")
	private LocalDateTime deleteAt;
}
