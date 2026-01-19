package com.example.admin.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "合伙人用户列表VO")
public class UserPartnerListVO extends AbstractUserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户ID", example = "1")
	private Long id;

	@Schema(description = "用户名", example = "partner001")
	private String username;

	@Schema(description = "手机号（已脱敏）", example = "138****8000")
	private String phone;

	@Schema(description = "头像URL", example = "http://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "合伙人姓名", example = "王五")
	private String partnerName;

	@Schema(description = "推广码", example = "ABC123")
	private String inviteCode;

	@Schema(description = "推广码路径", example = "/invite/ABC123.jpg")
	private String inviteCodePath;

	@Schema(description = "银行卡号（已脱敏）", example = "6222***********1234")
	private String cardNumber;

	@Schema(description = "分佣比例(%)", example = "10.00")
	private BigDecimal commissionRate;

	@Schema(description = "上级合伙人ID", example = "0")
	private Long parentId;

	@Schema(description = "合伙人状态:1启用 0禁用")
	private Integer partnerStatus;

	@Schema(description = "用户状态")
	private Integer status;

	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	@Schema(description = "用户类型:1-系统用户 2-普通用户/服务者 3-商家 4-骑手")
	private Integer userType;
}
