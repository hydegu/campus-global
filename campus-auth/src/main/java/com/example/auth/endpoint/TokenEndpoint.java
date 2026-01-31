
package com.example.auth.endpoint;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.TemporalAccessorUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.entity.SysOauthClientDetails;
import com.example.admin.api.feign.RemoteClientDetailsService;
import com.example.admin.api.vo.TokenVo;
import com.example.auth.support.handler.AuthenticationFailureEventHandler;
import com.example.common.core.constant.CacheConstants;
import com.example.common.core.constant.CommonConstants;
import com.example.common.core.constant.SecurityConstants;
import com.example.common.core.util.RedisUtils;
import com.example.common.core.util.Result;
import com.example.common.core.util.SpringContextHolder;
import com.example.common.security.annotation.Inner;
import com.example.common.security.util.OAuth2EndpointUtils;
import com.example.common.security.util.OAuth2ErrorCodesExpand;
import com.example.common.security.util.OAuthClientException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

/**
 * OAuth2 令牌端点控制器，提供令牌相关操作
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(description = "oauth", name = "OAuth2 令牌端点控制器管理模块")
public class TokenEndpoint {

	private final HttpMessageConverter<OAuth2AccessTokenResponse> accessTokenHttpResponseConverter = new OAuth2AccessTokenResponseHttpMessageConverter();

	private final AuthenticationFailureHandler authenticationFailureHandler = new AuthenticationFailureEventHandler();

	private final OAuth2AuthorizationService authorizationService;

	private final CacheManager cacheManager;

	/**
	 * 注销并删除令牌
	 * @param authHeader 认证头信息，包含Bearer token
	 * @return 返回操作结果，包含布尔值表示是否成功
	 */
	@DeleteMapping("/token/logout")
	@Operation(summary = "注销并删除令牌", description = "注销并删除令牌")
	public Result<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		if (StrUtil.isBlank(authHeader)) {
			return Result.ok();
		}

		String tokenValue = authHeader.replace(OAuth2AccessToken.TokenType.BEARER.getValue(), StrUtil.EMPTY).trim();
		return removeToken(tokenValue);
	}

	/**
	 * 检查令牌有效性
	 * @param token 待验证的令牌
	 * @param response HTTP响应对象
	 * @param request HTTP请求对象
	 * @throws InvalidBearerTokenException 令牌无效或缺失时抛出异常
	 */
	@SneakyThrows
	@GetMapping("/token/check_token")
	@Operation(summary = "检查令牌有效性", description = "检查令牌有效性")
	public void checkToken(String token, HttpServletResponse response, HttpServletRequest request) {
		ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);

		if (StrUtil.isBlank(token)) {
			httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
			this.authenticationFailureHandler.onAuthenticationFailure(request, response,
					new InvalidBearerTokenException(OAuth2ErrorCodesExpand.TOKEN_MISSING));
			return;
		}
		OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);

		// 如果令牌不存在 返回401
		if (authorization == null || authorization.getAccessToken() == null) {
			this.authenticationFailureHandler.onAuthenticationFailure(request, response,
					new InvalidBearerTokenException(OAuth2ErrorCodesExpand.INVALID_BEARER_TOKEN));
			return;
		}

		Map<String, Object> claims = authorization.getAccessToken().getClaims();
		OAuth2AccessTokenResponse sendAccessTokenResponse = OAuth2EndpointUtils.sendAccessTokenResponse(authorization,
				claims);
		this.accessTokenHttpResponseConverter.write(sendAccessTokenResponse, MediaType.APPLICATION_JSON, httpResponse);
	}

	/**
	 * 删除令牌
	 * @param token 令牌
	 * @return 删除结果
	 */
	@DeleteMapping("/token/remove/{token}")
	@Inner
	@Operation(summary = "删除令牌", description = "删除令牌")
	public Result<Boolean> removeToken(@PathVariable("token") String token) {
		OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);
		if (authorization == null) {
			return Result.ok();
		}

		OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
		if (accessToken == null || StrUtil.isBlank(accessToken.getToken().getTokenValue())) {
			return Result.ok();
		}
		// 清空用户信息（立即删除）
		cacheManager.getCache(CacheConstants.USER_DETAILS).evictIfPresent(authorization.getPrincipalName());
		// 清空access token
		authorizationService.remove(authorization);
		// 处理自定义退出事件，保存相关日志
		SpringContextHolder.publishEvent(new LogoutSuccessEvent(new PreAuthenticatedAuthenticationToken(
				authorization.getPrincipalName(), authorization.getRegisteredClientId())));
		return Result.ok();
	}

	/**
	 * 将OAuth2Authorization转换为TokenVo
	 * @param authorization OAuth2授权对象
	 * @return TokenVo对象
	 */
	private TokenVo convertToTokenVo(OAuth2Authorization authorization) {
		TokenVo tokenVo = new TokenVo();
		tokenVo.setClientId(authorization.getRegisteredClientId());
		tokenVo.setId(authorization.getId());
		tokenVo.setUsername(authorization.getPrincipalName());
		OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
		tokenVo.setAccessToken(accessToken.getToken().getTokenValue());

		String expiresAt = TemporalAccessorUtil.format(accessToken.getToken().getExpiresAt(),
				DatePattern.NORM_DATETIME_PATTERN);
		tokenVo.setExpiresAt(expiresAt);
		return tokenVo;
	}

}
