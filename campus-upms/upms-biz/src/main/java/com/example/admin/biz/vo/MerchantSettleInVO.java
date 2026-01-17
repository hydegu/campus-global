package com.example.admin.biz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "商家入驻申请列表VO")
public class MerchantSettleInVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "商家ID", example = "1")
	private Long id;

	@Schema(description = "商家名称", example = "校园便利店")
	private String orgName;

	@Schema(description = "联系人", example = "李四")
	private String contactPerson;

	@Schema(description = "联系方式（电话/手机）", example = "13900139000")
	private String contactPhone;

	@Schema(description = "所在省", example = "北京市")
	private String province;

	@Schema(description = "所在市", example = "海淀区")
	private String city;

	@Schema(description = "所在区", example = "中关村")
	private String district;

	@Schema(description = "详细地址", example = "中关村大街1号")
	private String address;

	@Schema(description = "所属合伙人ID", example = "1")
	private Long partnerId;

	@Schema(description = "所属合伙人姓名（关联查询）", example = "张三")
	private String partnerName;

	@Schema(description = "审核记录ID", example = "1")
	private Long auditId;

	@Schema(description = "审核状态（0-待审核，1-审核通过，2-审核拒绝）", example = "1")
	private Integer auditStatus;

	@Schema(description = "审核意见", example = "审核通过")
	private String auditOpinion;

	@Schema(description = "申请入驻时间（直接返回创建时间 created_at）")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime applyTime;

	@Schema(description = "审核时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime auditTime;

	@Schema(description = "商家状态（0-禁用，1-启用）", example = "1")
	private Integer status;

	@Schema(description = "打款账户", example = "6222021234567890123")
	private String settlementAccount;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;
}
