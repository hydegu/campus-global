package com.example.admin.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "菜单VO")
public class SysMenuVO {

	@Schema(description = "菜单ID", example = "1")
	private Long id;

	@Schema(description = "父菜单ID(0为根菜单）", example = "0")
	private Long parentId;

	@Schema(description = "类型:0-目录,1-菜单,2-按钮", example = "1")
	private Integer menuType;

	@Schema(description = "图标", example = "el-icon-menu")
	private String menuIcon;

	@Schema(description = "菜单名称", example = "用户管理")
	private String menuName;

	@Schema(description = "排序", example = "1")
	private Integer sortOrder;

	@Schema(description = "0:是外链 1:站内路由", example = "1")
	private Integer isFrame;

	@Schema(description = "权限", example = "system:user:list")
	private String perms;

	@Schema(description = "组件路径", example = "/system/user/index")
	private String component;

	@Schema(description = "访问路径", example = "/system/user")
	private String path;

	@Schema(description = "状态:0-隐藏,1-显示", example = "1")
	private Integer status;

	@Schema(description = "创建时间")
	private LocalDateTime createdAt;

	@Schema(description = "子菜单列表")
	private List<SysMenuVO> children;
}
