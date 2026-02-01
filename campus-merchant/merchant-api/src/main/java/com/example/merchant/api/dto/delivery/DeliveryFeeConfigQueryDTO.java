package com.example.merchant.api.dto.delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 配送费配置查询条件DTO
 *
 * @author campus-merchant
 */
@Data
@Schema(name = "DeliveryFeeConfigQueryDTO", description = "配送费配置查询条件DTO")
public class DeliveryFeeConfigQueryDTO {

	@Schema(description = "配置ID", example = "1")
	private Long configId;

	@Schema(description = "配置名称（模糊查询）", example = "标准配送")
	private String configName;

	@Schema(description = "状态：0-禁用，1-启用", example = "1")
	private Integer status;

	@Schema(description = "页码", example = "1")
	private Integer pageNum;

	@Schema(description = "每页大小", example = "10")
	private Integer pageSize;

	@Schema(description = "排序字段", example = "createAt")
	private String sortField;

	@Schema(description = "排序方向（asc/desc）", example = "desc")
	private String sortOrder;

}