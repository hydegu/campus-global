package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "C端用户表")
public class UserApp implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "主键ID")
	private Long id;

	@TableField("base_user_id")
	@Schema(description = "关联base_user表ID")
	private Long baseUserId;

	@Schema(description = "性别:0-未知 1-男 2-女")
	private Integer gender;

	@Schema(description = "微信openid")
	private String openid;

	@TableField("stu_code")
	@Schema(description = "学号")
	private String stuCode;

	@Schema(description = "余额")
	private BigDecimal balance;

	@TableField("total_amount")
	@Schema(description = "累计收益")
	private BigDecimal totalAmount;

	@TableField("school_id")
	@Schema(description = "学校ID")
	private Long schoolId;

	@TableField("audit_id")
	@Schema(description = "关联审核表id（审核服务人员资格）")
	private Long auditId;

	@TableField("real_name")
	@Schema(description = "真实姓名")
	private String realName;

	@Schema(description = "身份证号")
	private String idCard;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "更新时间")
	private LocalDateTime updateTime;

	@TableLogic
	@TableField("delete_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "删除时间")
	private LocalDateTime deleteTime;
}
