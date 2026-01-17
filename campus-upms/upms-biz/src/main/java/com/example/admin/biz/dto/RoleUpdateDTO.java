package com.example.admin.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "角色更新DTO")
public class RoleUpdateDTO {

	@Schema(description = "角色名称", example = "超级管理员")
	private String roleName;

	@Schema(description = "角色标识", example = "admin")
	private String roleCode;

	@Schema(description = "菜单ID集合", example = "[1, 2, 3]")
	private List<Long> menuIds;

	@Schema(description = "排序权重数", example = "1")
	private Integer sort;

	@Schema(description = "系统角色状态", example = "1")
	private Integer status;
}
