package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "编辑商家用户DTO")
public class UpdateUserMchDTO implements Serializable {

	@Schema(description = "用户名", example = "mch001")
	private String username;

	@Schema(description = "手机号", example = "13800138000")
	private String phone;

	@Schema(description = "头像", example = "https://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "状态", example = "1")
	private Integer status;

	@Schema(description = "商家名称", example = "美食餐厅")
	private String shopName;

	@Schema(description = "营业执照路径", example = "https://example.com/license.jpg")
	private String businessLicenseUrls;

	@Schema(description = "省", example = "北京市")
	private String province;

	@Schema(description = "市", example = "北京市")
	private String city;

	@Schema(description = "区", example = "海淀区")
	private String district;

	@Schema(description = "详细地址", example = "中关村大街1号")
	private String detailAddress;

	@Schema(description = "打款账户", example = "6222021234567890123")
	private String paymentAccount;

	@Schema(description = "密码", example = "123456")
	private String password;

	@Schema(description = "经营开始时间", example = "08:00")
	private String businessStartTime;

	@Schema(description = "经营结束时间", example = "22:00")
	private String businessEndTime;

	@Schema(description = "商户logo", example = "https://example.com/logo.jpg")
	private String logo;

	@Schema(description = "联系人姓名", example = "张三")
	private String contactName;

	@Schema(description = "身份证号", example = "110101199001011234")
	private String idCard;

	@Schema(description = "合伙人ID", example = "1")
	private Long partnerId;

	@Schema(description = "最低起送金额(元)", example = "20.00")
	private BigDecimal minimumOrderAmount;

	@Schema(description = "余额", example = "100.00")
	private BigDecimal balance;

	@Schema(description = "累计总收入", example = "1000.00")
	private BigDecimal totalAmount;

	@Schema(description = "角色ID列表", example = "[1, 2, 3]")
	private List<Long> roleIds;
}
