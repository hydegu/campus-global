package com.example.auth.support.core;

import com.example.common.core.constant.SecurityConstants;
import com.example.common.security.service.ExtraUser;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2 Token 自定义增强实现类
 */
public class CustomeOAuth2TokenCustomizer implements OAuth2TokenCustomizer<OAuth2TokenClaimsContext> {

	/**
	 * 自定义OAuth 2.0 Token属性
	 * @param context 包含OAuth 2.0 Token属性的上下文
	 */
	@Override
	public void customize(OAuth2TokenClaimsContext context) {
		OAuth2TokenClaimsSet.Builder claims = context.getClaims();
		String clientId = context.getAuthorizationGrant().getName();
		claims.claim(SecurityConstants.CLIENT_ID, clientId);
		// 客户端模式不返回具体用户信息
		if (SecurityConstants.CLIENT_CREDENTIALS.equals(context.getAuthorizationGrantType().getValue())) {
			return;
		}

		ExtraUser extraUser = (ExtraUser) context.getPrincipal().getPrincipal();
		claims.claim(SecurityConstants.DETAILS_USER_ID, extraUser.getId());
		claims.claim(SecurityConstants.USERNAME, extraUser.getUsername());
		
		// 将用户信息存储到 additionalParameters 中
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("id", extraUser.getId());
		userInfo.put("username", extraUser.getUsername());
		userInfo.put("phone", extraUser.getPhone());
		claims.claim(SecurityConstants.DETAILS_USER, userInfo);
	}

}
