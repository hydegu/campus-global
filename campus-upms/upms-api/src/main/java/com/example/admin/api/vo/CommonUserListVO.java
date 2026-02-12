package com.example.admin.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "用户列表VO（统一查询接口）")
public class CommonUserListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@Schema(description = "主键ID")
	private Long id;

	/**
	 * 用户名
	 */
	@Schema(description = "用户名")
	private String username;

	/**
	 * 昵称
	 */
	@Schema(description = "昵称")
	private String nickname;

	/**
	 * 手机号
	 */
	@Schema(description = "手机号")
	private String phone;

	/**
	 * 邮箱
	 */
	@Schema(description = "邮箱")
	private String email;

	/**
	 * 用户类型
	 */
	@Schema(description = "1-系统用户 2-普通用户/服务者 3-商家 4-骑手 5-合伙人")
	private Integer userType;

	/**
	 * 头像
	 */
	@Schema(description = "头像URL")
	private String avatar;

	/**
	 * 状态
	 */
	@Schema(description = "状态,1:正常,0:停用")
	private Integer status;

	/**
	 * 最后登录时间
	 */
	@Schema(description = "最后登录时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLoginTime;

	/**
	 * 最后登录IP
	 */
	@Schema(description = "最后登录IP")
	private String lastLoginIp;

	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
}