package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Schema(description = "用户查询DTO")
public class UserQueryDTO {

	@Min(value = 1, message = "页码最小为1")
	@Schema(description = "当前页码，从1开始", example = "1")
	private Integer page = 1;

	@Min(value = 1, message = "每页条数最小为1")
	@Max(value = 100, message = "每页条数最大为100")
	@Schema(description = "每页条数", example = "10")
	private Integer size = 10;

	@Schema(description = "关键字搜索（支持用户名、手机号、邮箱、昵称）")
	private String keyword;

	@Schema(description = "状态")
	private Integer status;

	@Schema(description = "用户类型:1-系统用户 2-普通用户/服务者 3-商家 4-骑手 5-合伙人")
	private Integer userType;
}