
package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色中间表
 */
@Data
@TableName("user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends Model<UserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@Schema(description = "用户id")
	private Long userId;

	/**
	 * 角色ID
	 */
	@Schema(description = "角色id")
	private Long roleId;

}
