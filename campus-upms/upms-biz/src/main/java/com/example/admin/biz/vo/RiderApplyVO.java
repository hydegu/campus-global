package com.example.admin.biz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "骑手申请审核列表VO")
public class RiderApplyVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "申请ID", example = "1025")
	private Long id;

	@Schema(description = "审核编号", example = "AU202312120002")
	private String auditNo;

	@Schema(description = "骑手ID", example = "2001")
	private Long riderId;

	@Schema(description = "骑手姓名", example = "李四骑手")
	private String riderName;

	@Schema(description = "骑手手机号", example = "138****1111")
	private String riderPhone;

	@Schema(description = "骑手性别", example = "男")
	private String gender;

	@Schema(description = "省市区", example = "河北省邯郸市丛台区")
	private String fullAddress;

	@Schema(description = "身份证正面URL", example = "http://example.com/id-front.jpg")
	private String idCardFrontUrl;

	@Schema(description = "身份证反面URL", example = "http://example.com/id-back.jpg")
	private String idCardBackUrl;

	@Schema(description = "审核状态：0-待审核，1-已通过，2-已拒绝", example = "1")
	private Integer auditStatus;

	@Schema(description = "审核状态文本", example = "已通过")
	private String auditStatusText;

	@Schema(description = "审核意见", example = "资质齐全，审核通过")
	private String auditOpinion;

	@Schema(description = "申请时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime applyTime;

	@Schema(description = "审核时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime auditTime;
}
