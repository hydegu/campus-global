package com.example.admin.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "C端用户列表VO")
public class UserAppListVO extends AbstractUserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户ID", example = "1")
	private Long id;

	@Schema(description = "用户名", example = "user001")
	private String username;

	@Schema(description = "手机号（已脱敏）", example = "138****8000")
	private String phone;

	@Schema(description = "昵称", example = "小明")
	private String nickname;

	@Schema(description = "邮箱", example = "user@example.com")
	private String email;

	@Schema(description = "学号", example = "20240001")
	private String code;

	@Schema(description = "头像URL", example = "http://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "性别", example = "男")
	private String gender;

	@Schema(description = "所属学校名称", example = "清华大学")
	private String schoolName;

	@Schema(description = "账户余额/元", example = "500")
	private BigDecimal balance;

	@Schema(description = "累计分佣收益/元", example = "1000")
	private BigDecimal commissionTotal;

	@Schema(description = "真实姓名")
	private String realName;

	@Schema(description = "用户类型:1-系统用户 2-普通用户/服务者 3-商家 4-骑手")
	private Integer userType;

	@Schema(description = "状态")
	private Integer status;

	@Schema(description = "创建时间")
	private LocalDateTime createTime;
}
