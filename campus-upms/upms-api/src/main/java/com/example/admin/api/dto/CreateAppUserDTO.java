package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建C端用户DTO
 */
@Data
@Schema(description = "创建C端用户DTO")
public class CreateAppUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户名", example = "student001", required = true)
	@NotBlank(message = "用户名不能为空")
	private String username;

	@Schema(description = "密码", example = "123456", required = true)
	@NotBlank(message = "密码不能为空")
	private String password;

	@Schema(description = "手机号", example = "13800138000", required = true)
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
	private String phone;

	@Schema(description = "邮箱", example = "student@example.com")
	private String email;

	@Schema(description = "昵称", example = "小明")
	private String nickname;

	@Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "状态，1-正常，0-停用", example = "1")
	private Integer status;

	@Schema(description = "真实姓名", example = "张三")
	private String realName;

	@Schema(description = "性别，0-未知，1-男，2-女", example = "1")
	private Integer gender;

	@Schema(description = "学校ID", example = "1")
	private Long schoolId;

	@Schema(description = "学号", example = "20240001")
	private String stuCode;

	@Schema(description = "角色ID列表", example = "[1, 2, 3]")
	private List<Long> roleIds;
}