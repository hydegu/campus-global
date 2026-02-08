package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "菜单更新DTO")
public class SysMenuUpdateDTO {

	@Schema(description = "菜单ID（可选，用于直接指定菜单ID）", example = "1")
	private Long id;

	@NotNull(message = "父菜单ID不能为空")
	@Schema(description = "父菜单ID(0为根菜单)", example = "0")
	private Long parentId;

	@NotNull(message = "菜单类型不能为空")
	@Schema(description = "类型:0-目录,1-菜单,2-按钮", example = "1")
	private Integer menuType;

	@Schema(description = "图标", example = "el-icon-menu")
	private String menuIcon;

	@NotNull(message = "菜单名称不能为空")
	@Schema(description = "菜单名称", example = "用户管理")
	private String menuName;

	@Schema(description = "排序", example = "1")
	private Integer sortOrder;

	@Schema(description = "0:是外链 1:站内路由", example = "1")
	private Integer isFrame;

	@Schema(description = "权限", example = "system:user:list")
	private String perms;

	@Schema(description = "访问路径", example = "/system/user")
	private String path;

	@Schema(description = "重定向路径", example = "/system/user/sys-user")
	private String redirect;

	@Schema(description = "状态:0-隐藏,1-显示", example = "1")
	private Integer status;
}
