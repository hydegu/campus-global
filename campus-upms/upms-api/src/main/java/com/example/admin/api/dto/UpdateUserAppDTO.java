package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "编辑C端用户DTO")
public class UpdateUserAppDTO implements Serializable {

	@Schema(description = "用户名", example = "user001")
	private String username;

	@Schema(description = "手机号", example = "13800138000")
	private String phone;

	@Schema(description = "头像", example = "https://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "状态", example = "1")
	private Integer status;

	@Schema(description = "学校ID", example = "1")
	private Long schoolId;

	@Schema(description = "真实姓名", example = "张三")
	private String realName;

	@Schema(description = "性别", example = "1")
	private Integer gender;

	@Schema(description = "学号", example = "20240001")
	private String stuCode;

	@Schema(description = "角色ID列表", example = "[1, 2, 3]")
	private List<Long> roleIds;
}
