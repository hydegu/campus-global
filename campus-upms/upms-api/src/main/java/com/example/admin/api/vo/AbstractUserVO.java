package com.example.admin.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "用户列表VO基类")
public abstract class AbstractUserVO {

	@Schema(description = "角色ID列表")
	private List<Long> roleIds;

	@Schema(description = "角色名称列表")
	private List<String> roleNames;
}
