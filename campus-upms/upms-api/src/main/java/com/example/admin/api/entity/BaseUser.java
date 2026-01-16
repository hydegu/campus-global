
package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 */
@Data
@Schema(description = "用户")
public class BaseUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "主键id")
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
	 * 密码
	 */
	@Schema(description = "密码")
	private String password;

	/**
	 * 状态
	 */
	@Schema(description = "状态,1:正常,0:停用")
	private Integer status;

	/**
	 * 头像
	 */
	@Schema(description = "头像地址")
	private String avatar;

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
	@Schema(description = "1-系统后台 2-普通用户/服务者 3-商家 4骑手")
	private Integer userType;

	/**
	 * 更新时间
	 */
	@Schema(description = "更新时间")
	@TableField(fill = FieldFill.UPDATE)
	private LocalDateTime updateAt;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间")
	private LocalDateTime createAt;


	/**
	 * 删除标记
	 */
	@TableLogic
	@Schema(description = "删除时间")
	private LocalDateTime deleteAt;

	/**
	 * 最后登陆时间
	 */
	 @Schema(description = "最后登陆时间")
	 private LocalDateTime lastLoginAt;
}
