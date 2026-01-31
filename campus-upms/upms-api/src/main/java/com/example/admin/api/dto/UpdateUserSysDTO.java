package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "编辑系统用户DTO")
public class UpdateUserSysDTO implements Serializable {

	@Schema(description = "用户名", example = "admin001")
	private String username;

	@Schema(description = "手机号", example = "13800138000")
	private String phone;

	@Schema(description = "头像", example = "https://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "状态", example = "1")
	private Integer status;

	@Schema(description = "真实姓名", example = "王五")
	private String realName;

	@Schema(description = "性别", example = "1")
	private Integer gender;

	@Schema(description = "出生日期", example = "1985-01-01")
	private String birthday;

	@Schema(description = "邮箱", example = "admin@example.com")
	private String email;

	@Schema(description = "角色ID列表", example = "[1, 2, 3]")
	private List<Long> roleIds;
}
