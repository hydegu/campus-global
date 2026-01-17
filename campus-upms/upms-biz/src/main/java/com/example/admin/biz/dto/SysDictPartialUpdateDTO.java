package com.example.admin.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Schema(description = "字典部分更新DTO")
public class SysDictPartialUpdateDTO {

	@Schema(description = "父字典ID（父字典为null）", example = "0")
	private Long parentId;

	@Schema(description = "字典键（父字典为null）", example = "pending")
	private String dictKey;

	@Schema(description = "字典值", example = "待处理")
	private String dictValue;

	@Schema(description = "备注", example = "订单状态字典")
	private String remarks;

	@Schema(description = "排序", example = "1")
	private Integer sortOrder;

	@Min(value = 0, message = "状态值不能小于0")
	@Max(value = 1, message = "状态值不能大于1")
	@Schema(description = "状态:0-禁用,1-启用", example = "1")
	private Integer status;
}
