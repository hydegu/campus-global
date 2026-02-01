package com.example.merchant.api.dto.delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 配送费规则状态变更DTO
 *
 * @author campus-merchant
 */
@Data
@Schema(name = "DeliveryFeeRuleStatusDTO", description = "配送费规则状态变更DTO")
public class DeliveryFeeRuleStatusDTO {

	@Schema(description = "状态：0-禁用，1-启用", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull(message = "状态不能为空")
	@Min(value = 0, message = "状态必须是0或1")
	@Max(value = 1, message = "状态必须是0或1")
	private Integer status;

}