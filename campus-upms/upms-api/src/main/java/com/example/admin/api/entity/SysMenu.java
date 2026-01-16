
package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

/**
 * 菜单权限表
 */
@Data
@Schema(description = "菜单")
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends Model<SysMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@TableId(value = "menu_id", type = IdType.ASSIGN_ID)
	@Schema(description = "菜单id")
	private Long id;

	/**
	 * 父菜单ID
	 */
	@Schema(description = "父菜单id")
	private Long parentId;

	/**
	 * 菜单类型 （0目录 1菜单 2按钮）
	 */
	@NotNull(message = "菜单类型不能为空")
	@Schema(description = "菜单类型,0:目录 1:菜单 2:按钮")
	private String menuType;

	/**
	 * 菜单图标
	 */
	@NotBlank(message = "菜单图标不能为空")
	@Schema(description = "菜单图标")
	private String menuIcon;

	/**
	 * 菜单名称
	 */
	@NotBlank(message = "菜单名称不能为空")
	@Schema(description = "菜单名称")
	private String menuName;

	/**
	 * 排序值
	 */
	@Schema(description = "排序值")
	private Integer sortOrder;

	/**
	 * 菜单名称
	 */
	@Schema(description = "菜单名称")
	private String enName;

	/**
	 * 菜单权限标识
	 */
	@Schema(description = "菜单权限标识")
	private String permission;

	/**
	 * 0-外联 1-站内路由
	 */
	@Schema(description = "路由类型,0:外联 1:站内路由")
	private String isFrame;

	/**
	 * 路由路径
	 */
	@Schema(description = "前端路由标识路径")
	private String path;

	/**
	 * 状态
	 */
	@Schema(description = "状态,1:正常,0:停用")
	private Integer status;

	/**
	 * 组件路径
	 */
	@Schema(description = "组件路径")
	private String component;

	/**
	 * 更新时间
	 */
	@Schema(description = "更新时间")
	@TableField(fill = FieldFill.UPDATE)
	private LocalDateTime updateAt;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间")
	private LocalDateTime createAt;

	/**
	 * 删除标记
	 */
	@TableLogic
	@Schema(description = "删除时间")
	private LocalDateTime deleteAt;


}
