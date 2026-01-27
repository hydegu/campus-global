package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建骑手用户DTO
 */
@Data
@Schema(description = "创建骑手用户DTO")
public class CreateRiderUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户名", example = "rider001", required = true)
	@NotBlank(message = "用户名不能为空")
	private String username;

	@Schema(description = "密码", example = "123456", required = true)
	@NotBlank(message = "密码不能为空")
	private String password;

	@Schema(description = "手机号", example = "13800138000", required = true)
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
	private String phone;

	@Schema(description = "邮箱", example = "rider@example.com")
	private String email;

	@Schema(description = "昵称", example = "小李")
	private String nickname;

	@Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "状态，1-正常，0-停用", example = "1")
	private Integer status;

	@Schema(description = "真实姓名", example = "李四", required = true)
	@NotBlank(message = "真实姓名不能为空")
	private String realName;

	@Schema(description = "性别，0-未知，1-男，2-女", example = "1", required = true)
	@NotNull(message = "性别不能为空")
	private Integer gender;

	@Schema(description = "身份证号", example = "110101199001011234", required = true)
	@NotBlank(message = "身份证号不能为空")
	@Pattern(regexp = "^\\d{17}[\\dXx]$", message = "身份证号格式不正确")
	private String idCard;

	@Schema(description = "身份证正面照片URL", example = "https://example.com/idcard_front.jpg")
	private String idCardFront;

	@Schema(description = "身份证背面照片URL", example = "https://example.com/idcard_back.jpg")
	private String idCardBack;

	@Schema(description = "银行卡号", example = "6222021234567890123")
	private String cardNumber;

	@Schema(description = "紧急联系人姓名", example = "王五")
	private String emergencyContactName;

	@Schema(description = "紧急联系人电话", example = "13900139000")
	@Pattern(regexp = "^1[3-9]\\d{9}$", message = "紧急联系人电话格式不正确")
	private String emergencyContactPhone;
}