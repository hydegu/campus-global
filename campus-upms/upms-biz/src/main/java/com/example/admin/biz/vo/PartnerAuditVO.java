package com.example.admin.biz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "合伙人审核列表VO")
public class PartnerAuditVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "合伙人ID", example = "1")
	private Long id;

	@Schema(description = "合伙人姓名", example = "张三")
	private String partnerName;

	@Schema(description = "手机号", example = "13800138000")
	private String phone;

	@Schema(description = "邮箱", example = "zhangsan@example.com")
	private String email;

	@Schema(description = "头像URL", example = "http://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "所在省", example = "北京市")
	private String province;

	@Schema(description = "所在市", example = "海淀区")
	private String city;

	@Schema(description = "所在区", example = "中关村")
	private String district;

	@Schema(description = "详细地址", example = "中关村大街1号")
	private String address;

	@Schema(description = "审核记录ID", example = "1")
	private Long auditId;

	@Schema(description = "审核状态：0-待审核，1-审核通过，2-审核拒绝", example = "1")
	private Integer auditStatus;

	@Schema(description = "审核意见", example = "审核通过")
	private String auditOpinion;

	@Schema(description = "申请时间（使用审核记录的created_at）")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime applyTime;

	@Schema(description = "审核时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime auditTime;

	@Schema(description = "打款账户", example = "6222021234567890123")
	private String settlementAccount;
}
