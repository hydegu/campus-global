package com.example.merchant.api.dto.delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 配送费规则查询条件DTO
 *
 * @author campus-merchant
 */
@Data
@Schema(name = "DeliveryFeeRuleQueryDTO", description = "配送费规则查询条件DTO")
public class DeliveryFeeRuleQueryDTO {

	@Schema(description = "配置ID", example = "1")
	private Long configId;

	@Schema(description = "规则类型：1-距离，2-时间", example = "1")
	private Integer ruleType;

	@Schema(description = "时段类型：1-白天，2-夜间", example = "1")
	private Integer timeType;

	@Schema(description = "页码", example = "1")
	private Integer pageNum;

	@Schema(description = "每页大小", example = "10")
	private Integer pageSize;

	@Schema(description = "排序字段", example = "sortOrder")
	private String sortField;

	@Schema(description = "排序方向（asc/desc）", example = "asc")
	private String sortOrder;

}