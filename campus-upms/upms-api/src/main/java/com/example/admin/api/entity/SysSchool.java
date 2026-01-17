package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "学校表")
public class SysSchool implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "学校ID")
	private Long id;

	@TableField("school_name")
	@Schema(description = "校名")
	private String schoolName;

	@TableField("address_id")
	@Schema(description = "学校地址id")
	private Long addressId;

	@Schema(description = "状态:1启用 0禁用")
	private Integer status;

	@TableField(value = "create_at", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "创建时间")
	private LocalDateTime createAt;

	@TableField(value = "update_at", fill = FieldFill.UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "更新时间")
	private LocalDateTime updateAt;

	@TableLogic
	@TableField("delete_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "软删除时间")
	private LocalDateTime deleteAt;
}
