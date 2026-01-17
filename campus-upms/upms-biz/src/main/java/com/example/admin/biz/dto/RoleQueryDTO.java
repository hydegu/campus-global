package com.example.admin.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "角色查询DTO")
public class RoleQueryDTO {

	@NotNull(message = "页码不能为空")
	@Min(value = 1, message = "页码必须大于0")
	@Schema(description = "当前页码，从1开始", example = "1")
	private Integer page;

	@NotNull(message = "每页条数不能为空")
	@Min(value = 1, message = "每页条数必须大于0")
	@Schema(description = "每页条数", example = "10")
	private Integer size;

	@Schema(description = "角色名称")
	private String roleName;

	@Schema(description = "角色标识")
	private String roleCode;

	@Schema(description = "状态")
	private Integer status;
}
