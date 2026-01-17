package com.example.admin.biz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Schema(description = "商家用户列表VO")
public class UserMchListVO extends AbstractUserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户ID", example = "1")
	private Long id;

	@Schema(description = "用户名", example = "merchant001")
	private String username;

	@Schema(description = "手机号（已脱敏）", example = "138****8000")
	private String phone;

	@Schema(description = "头像URL", example = "http://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "商户名称", example = "美味餐厅")
	private String mchName;

	@Schema(description = "商户logo", example = "http://example.com/logo.jpg")
	private String mchLogo;

	@Schema(description = "联系人姓名", example = "张三")
	private String contactName;

	@Schema(description = "最低起送金额", example = "20.00")
	private BigDecimal minimumOrderAmount;

	@Schema(description = "是否营业 0-休息 1-营业")
	private Integer isOpen;

	@Schema(description = "开始营业时间")
	private LocalTime businessStartTime;

	@Schema(description = "结束营业时间")
	private LocalTime businessEndTime;

	@Schema(description = "状态")
	private Integer status;

	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	@Schema(description = "用户类型:1-系统用户 2-普通用户/服务者 3-商家 4-骑手")
	private Integer userType;
}
