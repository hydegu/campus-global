package com.example.admin.api.vo;

import lombok.Data;

/**
 * 令牌管理VO
 */
@Data
public class TokenVo {

	private String id;

	private Long userId;

	private String clientId;

	private String username;

	private String accessToken;

	private String expiresAt;

}
