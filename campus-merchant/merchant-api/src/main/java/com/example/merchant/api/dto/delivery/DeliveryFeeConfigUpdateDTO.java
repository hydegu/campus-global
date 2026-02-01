package com.example.merchant.api.dto.delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 更新配送费配置请求DTO
 *
 * @author campus-merchant
 */
@Data
@Schema(name = "DeliveryFeeConfigUpdateDTO", description = "更新配送费配置请求DTO")
public class DeliveryFeeConfigUpdateDTO {

	@Schema(description = "配置名称", example = "标准配送")
	private String configName;

	@Schema(description = "起步价", example = "5.00")
	@DecimalMin(value = "0.00", message = "起步价不能小于0")
	private BigDecimal baseFee;

	@Schema(description = "起步距离(公里)", example = "3.00")
	@DecimalMin(value = "0.00", message = "起步距离不能小于0")
	private BigDecimal baseDistance;

	@Schema(description = "状态：0-禁用，1-启用", example = "1")
	@Min(value = 0, message = "状态必须是0或1")
	@Max(value = 1, message = "状态必须是0或1")
	private Integer status;

}