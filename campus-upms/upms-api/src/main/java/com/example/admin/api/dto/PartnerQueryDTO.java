package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "合伙人查询DTO")
public class PartnerQueryDTO extends UserQueryDTO {

	@Schema(description = "返回结构类型: 0-平铺列表(默认), 1-树形结构", example = "0")
	private Integer treeType;
}