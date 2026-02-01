package com.example.merchant.api.dto.delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * 更新配送费规则请求DTO
 *
 * @author campus-merchant
 */
@Data
@Schema(name = "DeliveryFeeRuleUpdateDTO", description = "更新配送费规则请求DTO")
public class DeliveryFeeRuleUpdateDTO {

	@Schema(description = "规则类型：1-距离，2-时间", example = "1")
	@Min(value = 1, message = "规则类型必须是1或2")
	@Max(value = 2, message = "规则类型必须是1或2")
	private Integer ruleType;

	// 距离规则字段
	@Schema(description = "起始距离(公里)", example = "3.00")
	@DecimalMin(value = "0.00", message = "起始距离不能小于0")
	private BigDecimal distanceStart;

	@Schema(description = "结束距离(公里)", example = "10.00")
	@DecimalMin(value = "0.00", message = "结束距离不能小于0")
	private BigDecimal distanceEnd;

	@Schema(description = "每公里价格", example = "1.50")
	@DecimalMin(value = "0.00", message = "每公里价格不能小于0")
	private BigDecimal pricePerKm;

	// 时间规则字段
	@Schema(description = "开始时间", example = "08:00:00")
	private LocalTime timeStart;

	@Schema(description = "结束时间", example = "20:00:00")
	private LocalTime timeEnd;

	@Schema(description = "时段附加费", example = "2.00")
	@DecimalMin(value = "0.00", message = "时段附加费不能小于0")
	private BigDecimal timeFee;

	@Schema(description = "时段类型：1-白天，2-夜间", example = "1")
	@Min(value = 1, message = "时段类型必须是1或2")
	@Max(value = 2, message = "时段类型必须是1或2")
	private Integer timeType;

	@Schema(description = "排序", example = "10")
	private Integer sortOrder;

}