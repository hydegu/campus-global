
package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 字典表
 */
@Data
@Schema(description = "字典类型")
@EqualsAndHashCode(callSuper = true)
public class SysDict extends Model<SysDict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description = "字典编号")
	private Long id;

	/**
	 * 父字典id
	 */
	@Schema(description = "父字典id")
	private Long parentId;

	/**
	 * 备注
	 */
	@Schema(description = "备注")
	private String remarks;

	/**
	 * 字典键
	 */
	@Schema(description = "字典键")
	private String dictKey;

	/**
	 * 字典值
	 */
	@Schema(description = "字典值")
	private String dictValue;

	/**
	 * 排序值
	 */
	@Schema(description = "排序值")
	private Integer sortOrder;

	/**
	 * 层级
	 */
	@Schema(description = "层级")
	private Integer level;

	/**
	 * 状态
	 */
	@Schema(description = "状态")
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
