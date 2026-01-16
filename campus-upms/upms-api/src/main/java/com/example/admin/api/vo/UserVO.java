
package com.example.admin.api.vo;

import com.example.admin.api.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "前端用户展示对象")
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@Schema(description = "主键")
	private Long userId;

	/**
	 * 用户名
	 */
	@Schema(description = "用户名")
	private String username;

	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	private LocalDateTime createAt;

	/**
	 * 修改时间
	 */
	@Schema(description = "修改时间")
	private LocalDateTime updateAt;

	/**
	 * 删除时间
	 */
	@Schema(description = "删除标记，非null为已删除")
	private LocalDateTime deleteAt;

	/**
	 * 用户状态
	 */
	@Schema(description = "锁定标记,0:正常,1:已禁用")
	private Integer status;

	/**
	 * 手机号
	 */
	@Schema(description = "手机号")
	private String phone;

	/**
	 * 头像
	 */
	@Schema(description = "头像")
	private String avatar;

	/**
	 * 角色列表
	 */
	@Schema(description = "拥有的角色列表")
	private List<Role> roleList;

	/**
	 * 昵称
	 */
	@Schema(description = "昵称")
	private String nickname;

	/**
	 * 邮箱
	 */
	@Schema(description = "邮箱")
	private String email;

	/**
	 * 用户类型
	 */
	@Schema(description = "1-系统用户 2-普通用户/服务者 3-商家 4骑手")
	private Integer userType;

}
