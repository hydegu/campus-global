package com.example.merchant.api.dto.delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 批量新增配送费配置请求DTO
 *
 * @author campus-merchant
 */
@Data
@Schema(name = "DeliveryFeeConfigBatchAddDTO", description = "批量新增配送费配置请求DTO")
public class DeliveryFeeConfigBatchAddDTO {

	@Schema(description = "配送费配置列表", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotEmpty(message = "配送费配置列表不能为空")
	@Valid
	private List<DeliveryFeeConfigAddDTO> configs;

}