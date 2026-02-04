package com.example.admin.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "菜单VO - Vue Router格式")
public class SysMenuVO {

	@Schema(description = "菜单ID", example = "1")
	private Long id;

	@Schema(description = "父菜单ID(0为根菜单）", example = "0")
	private Long parentId;

	@Schema(description = "类型:0-目录,1-菜单,2-按钮", example = "1")
	private Integer menuType;

	@Schema(description = "路由路径", example = "/system/user")
	private String path;

	@Schema(description = "组件路径", example = "/system/user/index")
	private String component;

	@Schema(description = "路由名称", example = "用户管理")
	private String name;

	@Schema(description = "重定向路径", example = "/system/user/sys-user")
	private String redirect;

	@Schema(description = "菜单元信息")
	private MenuMeta meta;

	@Schema(description = "子菜单列表")
	private List<SysMenuVO> children;

	@Data
	@Schema(description = "菜单元信息")
	public static class MenuMeta {

		@Schema(description = "菜单标题", example = "用户管理")
		private String title;

		@Schema(description = "菜单图标", example = "el-icon-menu")
		private String icon;

		@Schema(description = "是否iframe", example = "true")
		private Boolean isIframe;

		@Schema(description = "角色权限列表", example = "[\"system:user:list\"]")
		private List<String> roles;
	}
}