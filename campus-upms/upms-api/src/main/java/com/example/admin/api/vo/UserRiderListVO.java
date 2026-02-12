package com.example.admin.api.vo;

import com.example.admin.api.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "骑手用户列表VO")
public class UserRiderListVO extends AbstractUserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户ID", example = "1")
	private Long id;

	@Schema(description = "用户名", example = "rider001")
	private String username;

	@Schema(description = "手机号（已脱敏）", example = "138****8000")
	private String phone;

	@Schema(description = "昵称", example = "小李")
	private String nickname;

	@Schema(description = "邮箱", example = "rider@example.com")
	private String email;

	@Schema(description = "头像URL", example = "http://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "性别", example = "男")
	private String gender;

	@Schema(description = "真实姓名", example = "李四")
	private String realName;

	@Schema(description = "身份证号（已脱敏）", example = "110***********1234")
	private String idCard;

	@Schema(description = "账户余额/元", example = "500")
	private BigDecimal balance;

	@Schema(description = "累计收益/元", example = "1000")
	private BigDecimal commissionTotal;

	@Schema(description = "紧急联系人姓名", example = "王五")
	private String emergencyContactName;

	@Schema(description = "紧急联系人电话（已脱敏）", example = "139****9000")
	private String emergencyContactPhone;

	@Schema(description = "状态")
	private Integer status;

	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	@Schema(description = "用户类型:1-系统用户 2-普通用户/服务者 3-商家 4-骑手")
	private Integer userType;

	@Schema(description = "地址信息")
	private Address address;
}
