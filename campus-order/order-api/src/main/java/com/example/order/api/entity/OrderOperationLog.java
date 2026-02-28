package com.example.order.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单操作审计日志
 */
@Data
@Schema(description = "订单操作审计日志")
@TableName("order_operation_log")
public class OrderOperationLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "日志ID")
	private Long id;

	@Schema(description = "订单ID")
	private Long orderId;

	@Schema(description = "订单编号")
	private String orderNo;

	@Schema(description = "操作类型:CREATE-创建,ACCEPT-接单,PICKUP-取货,DELIVER-送达,CONFIRM-确认,CANCEL-取消,PAY-支付")
	private String operationType;

	@Schema(description = "操作前状态")
	private Integer beforeStatus;

	@Schema(description = "操作后状态")
	private Integer afterStatus;

	@Schema(description = "操作人ID")
	private Long operatorId;

	@Schema(description = "操作人名称")
	private String operatorName;

	@Schema(description = "操作人类型:1-用户 2-骑手 3-商家 4-系统")
	private Integer operatorType;

	@Schema(description = "备注")
	private String remark;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间")
	private LocalDateTime createAt;
}
