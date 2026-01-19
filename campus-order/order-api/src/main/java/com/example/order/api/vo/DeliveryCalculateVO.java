package com.example.order.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "配送费计算结果VO")
public class DeliveryCalculateVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "距离（公里）")
	private BigDecimal distance;

	@Schema(description = "配送费")
	private BigDecimal deliveryFee;

	@Schema(description = "预计送达时间")
	private LocalDateTime estimatedDeliveryTime;
}
