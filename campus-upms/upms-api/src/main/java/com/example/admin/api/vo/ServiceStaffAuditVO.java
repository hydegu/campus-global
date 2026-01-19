package com.example.admin.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "服务人员审核列表VO")
public class ServiceStaffAuditVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户ID", example = "1")
	private Long id;

	@Schema(description = "人员名称", example = "王五")
	private String username;

	@Schema(description = "联系方式", example = "13900139000")
	private String phone;

	@Schema(description = "服务所在区域（学校名称）", example = "清华大学")
	private String schoolName;

	@Schema(description = "审核记录ID", example = "1")
	private Long auditId;

	@Schema(description = "审核状态：0-待审核，1-审核通过，2-审核拒绝", example = "1")
	private Integer auditStatus;

	@Schema(description = "审核意见", example = "审核通过")
	private String auditOpinion;

	@Schema(description = "申请时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@Schema(description = "审核时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime auditTime;
}
