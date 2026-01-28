package com.example.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "跑腿订单支付DTO")
public class ErrandPayDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "订单ID不能为空")
	@Schema(description = "订单ID", example = "10001")
	private Long orderId;

	@NotNull(message = "支付方式不能为空")
	@Schema(description = "支付方式:1-在线支付 2-微信 3-线下支付 4-其他", example = "2")
	private Integer payMethod;

	@Schema(description = "第三方支付流水号", example = "WX202601191200001")
	private String payChannelNo;
}