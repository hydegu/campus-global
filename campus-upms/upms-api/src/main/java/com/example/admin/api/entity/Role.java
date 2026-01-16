
package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 角色表
 */
@Data
@Schema(description = "角色")
@EqualsAndHashCode(callSuper = true)
public class Role extends Model<Role> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "角色编号")
	private Long id;

	@NotBlank(message = "角色名称不能为空")
	@Schema(description = "角色名称")
	private String roleName;

	@NotBlank(message = "角色标识不能为空")
	@Schema(description = "角色标识")
	private String roleCode;

	/**
	 * 排序值
	 */
	@Schema(description = "排序值")
	private Integer sortOrder;


	/**
	 * 状态
	 */
	@Schema(description = "状态,1:正常,0:停用")
	private Integer status;

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
