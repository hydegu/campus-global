package com.example.admin.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "角色新增DTO")
public class RoleAddDTO {

	@NotBlank(message = "角色名称不能为空")
	@Schema(description = "角色名称", example = "超级管理员")
	private String roleName;

	@NotBlank(message = "角色标识不能为空")
	@Schema(description = "角色标识", example = "admin")
	private String roleCode;

	@NotEmpty(message = "菜单ID集合不能为空")
	@Schema(description = "菜单ID集合", example = "[1, 2, 3]")
	private List<Long> menuIds;

	@NotNull(message = "排序不能为空")
	@Schema(description = "排序权重数", example = "1")
	private Integer sort;

	@NotNull(message = "状态不能为空")
	@Schema(description = "系统角色状态", example = "1")
	private Integer status;
}
