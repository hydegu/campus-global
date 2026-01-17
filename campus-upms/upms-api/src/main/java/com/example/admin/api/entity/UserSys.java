package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "系统用户表")
public class UserSys implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "主键ID")
	private Long id;

	@TableField("base_user_id")
	@Schema(description = "关联base_user表ID")
	private Long baseUserId;

	@TableField("real_name")
	@Schema(description = "真实姓名")
	private String realName;

	@Schema(description = "性别")
	private Integer gender;
}
