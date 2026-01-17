package com.example.admin.biz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "角色VO")
public class RoleVO {

	@Schema(description = "角色ID", example = "1")
	private Long id;

	@Schema(description = "角色名称", example = "超级管理员")
	private String roleName;

	@Schema(description = "权限字符", example = "admin")
	private String roleCode;

	@Schema(description = "排序", example = "1")
	private Integer sortOrder;

	@Schema(description = "状态", example = "1")
	private Integer status;

	@Schema(description = "关联的菜单ID集合", example = "[1, 2, 3]")
	private List<Long> menuIds;

	@Schema(description = "关联的菜单信息列表")
	private List<SysMenuVO> menus;

	@Schema(description = "创建时间")
	private LocalDateTime createdAt;

	@Schema(description = "更新时间")
	private LocalDateTime updatedAt;
}
