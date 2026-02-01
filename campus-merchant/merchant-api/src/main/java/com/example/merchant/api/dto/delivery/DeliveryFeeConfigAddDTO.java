package com.example.merchant.api.dto.delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新增配送费配置请求DTO
 *
 * @author campus-merchant
 */
@Data
@Schema(name = "DeliveryFeeConfigAddDTO", description = "新增配送费配置请求DTO")
public class DeliveryFeeConfigAddDTO {

	@Schema(description = "配置名称", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "配置名称不能为空")
	private String configName;

	@Schema(description = "起步价", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull(message = "起步价不能为空")
	@DecimalMin(value = "0.00", message = "起步价不能小于0")
	private BigDecimal baseFee;

	@Schema(description = "起步距离(公里)", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull(message = "起步距离不能为空")
	@DecimalMin(value = "0.00", message = "起步距离不能小于0")
	private BigDecimal baseDistance;

	@Schema(description = "状态：0-禁用，1-启用", example = "1")
	private Integer status;

}