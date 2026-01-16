
package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 客户端信息
 */
@Data
@Schema(description = "客户端信息")
@EqualsAndHashCode(callSuper = true)
public class SysOauthClientDetails extends Model<SysOauthClientDetails> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "id")
	private Long id;

	/**
	 * 客户端ID
	 */
	@NotBlank(message = "client_id 不能为空")
	@Schema(description = "客户端id")
	private String clientId;

	/**
	 * 客户端密钥
	 */
	@NotBlank(message = "client_secret 不能为空")
	@Schema(description = "客户端密钥")
	private String clientSecret;

	/**
	 * 作用域
	 */
	@NotBlank(message = "scope 不能为空")
	@Schema(description = "作用域")
	private String scope;

	/**
	 * 授权方式[A,B,C]
	 */
	@Schema(description = "授权方式")
	private String[] authorizedGrantTypes;

	/**
	 * 回调地址
	 */
	@Schema(description = "回调地址")
	private String webServerRedirectUri;

	/**
	 * 请求令牌有效时间
	 */
	@Schema(description = "请求令牌有效时间")
	private Integer accessTokenValidity;

	/**
	 * 刷新令牌有效时间
	 */
	@Schema(description = "刷新令牌有效时间")
	private Integer refreshTokenValidity;

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


}
