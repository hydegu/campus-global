
package com.example.admin.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单中间表
 */
@Data
@Schema(description = "角色菜单")
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends Model<RoleMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@Schema(description = "角色id")
	private Long roleId;

	/**
	 * 菜单ID
	 */
	@Schema(description = "菜单id")
	private Long menuId;

}
