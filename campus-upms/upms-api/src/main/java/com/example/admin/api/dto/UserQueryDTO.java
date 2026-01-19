package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "用户查询DTO")
public class UserQueryDTO {

	@NotNull(message = "页码不能为空")
	@Min(value = 1, message = "页码最小为1")
	@Schema(description = "当前页码，从1开始", example = "1")
	private Integer page;

	@NotNull(message = "每页条数不能为空")
	@Min(value = 1, message = "每页条数最小为1")
	@Max(value = 100, message = "每页条数最大为100")
	@Schema(description = "每页条数", example = "10")
	private Integer size;

	@Schema(description = "用户名")
	private String username;

	@Schema(description = "手机号")
	private String phone;

	@Schema(description = "状态")
	private Integer status;

	@Schema(description = "学校ID")
	private Long schoolId;

	@Schema(description = "角色ID")
	private Long roleId;

	@Schema(description = "角色名称")
	private String roleName;

	@Schema(description = "用户类型:1-系统用户 2-普通用户/服务者 3-商家 4-骑手 5-合伙人")
	private Integer userType;

	@Schema(description = "开始时间")
	private String startTime;

	@Schema(description = "结束时间")
	private String endTime;
}
