package com.example.admin.biz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户列表VO基类")
public abstract class AbstractUserVO {

	@Schema(description = "角色ID")
	private String roleId;

	@Schema(description = "角色名称")
	private String roleName;
}
