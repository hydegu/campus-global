package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "重置用户密码DTO")
public class ResetPasswordDTO implements Serializable {

	@Schema(description = "新密码", example = "123456", required = true)
	@NotBlank(message = "新密码不能为空")
	private String newPassword;
}
