package com.example.auth.support.core;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.example.common.core.util.WebUtils;
import com.example.common.security.service.UserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.example.common.core.constant.SecurityConstants.PASSWORD;


/**
 * 基于DAO的认证提供者实现，用于处理用户名密码认证
 */
@Slf4j
public class DaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	/**
	 * 用户未找到时用于PasswordEncoder#matches(CharSequence, String)的明文密码，避免SEC-2056问题
	 */
	private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

	private final static BasicAuthenticationConverter basicConvert = new BasicAuthenticationConverter();

	/**
	 * 密码编码器
	 */
	private PasswordEncoder passwordEncoder;

	/**
	 * 用户未找到时的加密密码，用于避免SEC-2056问题，某些密码编码器在密码格式无效时会短路处理
	 */
	private volatile String userNotFoundEncodedPassword;

	private UserDetailsService userDetailsService;

	private UserDetailsPasswordService userDetailsPasswordService;

	public DaoAuthenticationProvider() {
		setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
	}

	/**
	 * 执行额外的身份验证检查
	 * @param userDetails 用户详细信息
	 * @param authentication 身份验证令牌
	 * @throws AuthenticationException 身份验证失败时抛出异常
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		String grantType = WebUtils.getRequest().get().getParameter(OAuth2ParameterNames.GRANT_TYPE);
		log.info("=== 开始额外认证检查 ===");
		log.info("授权类型: {}", grantType);
		log.info("用户名: {}", userDetails.getUsername());
		log.info("用户状态 - enabled: {}, accountNonExpired: {}, credentialsNonExpired: {}, accountNonLocked: {}", 
			userDetails.isEnabled(), userDetails.isAccountNonExpired(), 
			userDetails.isCredentialsNonExpired(), userDetails.isAccountNonLocked());

		if (!StrUtil.equals(PASSWORD, grantType)) {
			log.info("非密码模式，跳过密码验证");
			return;
		}

		if (authentication.getCredentials() == null) {
			log.error("认证失败：未提供凭证");
			log.error("用户名: {}", userDetails.getUsername());
			throw new BadCredentialsException("用户名或密码错误");
		}
		String presentedPassword = authentication.getCredentials().toString();
		log.info("输入的密码长度: {}", presentedPassword.length());
		log.info("输入的密码前3位: {}", presentedPassword.length() >= 3 ? presentedPassword.substring(0, 3) : "密码太短");
		log.info("数据库中的密码: {}", userDetails.getPassword());
		
		boolean passwordMatches = this.passwordEncoder.matches(presentedPassword, userDetails.getPassword());
		log.info("密码匹配结果: {}", passwordMatches);
		
		if (!passwordMatches) {
			log.error("认证失败：密码不匹配");
			log.error("用户名: {}", userDetails.getUsername());
			log.error("输入密码长度: {}", presentedPassword.length());
			log.error("数据库密码: {}", userDetails.getPassword());
			throw new BadCredentialsException("用户名或密码错误");
		}
		log.info("密码验证成功");
	}

	/**
	 * 根据用户名检索用户详情
	 * @param username 用户名
	 * @param authentication 认证令牌
	 * @return 用户详情信息
	 * @throws InternalAuthenticationServiceException
	 * 当无法获取请求、未注册UserDetailsService或加载用户失败时抛出
	 * @throws UsernameNotFoundException 当用户名不存在时抛出
	 */
	@SneakyThrows
	@Override
	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
		log.info("=== 开始检索用户 ===");
		log.info("用户名: {}", username);
		
		prepareTimingAttackProtection();
		HttpServletRequest request = WebUtils.getRequest()
			.orElseThrow(
					(Supplier<Throwable>) () -> new InternalAuthenticationServiceException("web request is empty"));

		String grantType = WebUtils.getRequest().get().getParameter(OAuth2ParameterNames.GRANT_TYPE);
		String clientId = WebUtils.getRequest().get().getParameter(OAuth2ParameterNames.CLIENT_ID);
		
		log.info("授权类型: {}", grantType);
		log.info("客户端ID: {}", clientId);

		if (StrUtil.isBlank(clientId)) {
			clientId = Optional.ofNullable(basicConvert.convert(request))
				.map(UsernamePasswordAuthenticationToken::getName)
				.orElse(null);
			log.info("从Basic Auth中获取客户端ID: {}", clientId);
		}

		Map<String, UserDetailsService> userDetailsServiceMap = SpringUtil
			.getBeansOfType(UserDetailsService.class);
		
		log.info("找到的UserDetailsService数量: {}", userDetailsServiceMap.size());
		userDetailsServiceMap.forEach((key, value) -> log.info("UserDetailsService: {} - {}", key, value.getClass().getName()));

		String finalClientId = clientId;
		Optional<UserDetailsService> optional = userDetailsServiceMap.values()
			.stream()
			.filter(service -> service.support(finalClientId, grantType))
			.max(Comparator.comparingInt(Ordered::getOrder));

		if (optional.isEmpty()) {
			log.error("未找到匹配的UserDetailsService");
			log.error("客户端ID: {}, 授权类型: {}", finalClientId, grantType);
			throw new InternalAuthenticationServiceException("UserDetailsService 错误，未注册");
		}
		
		UserDetailsService selectedService = optional.get();
		log.info("选择的UserDetailsService: {}", selectedService.getClass().getName());
		log.info("UserDetailsService Order: {}", selectedService.getOrder());

		try {
			UserDetails loadedUser = selectedService.loadUserByUsername(username);
			if (loadedUser == null) {
				log.error("UserDetailsService返回null");
				log.error("UserDetailsService: {}", selectedService.getClass().getName());
				throw new InternalAuthenticationServiceException(
						"UserDetailsService 返回 null，违反了接口契约");
			}
			log.info("用户检索成功");
			return loadedUser;
		}
		catch (UsernameNotFoundException ex) {
			log.error("用户未找到: {}", ex.getMessage());
			mitigateAgainstTimingAttack(authentication);
			throw ex;
		}
		catch (InternalAuthenticationServiceException ex) {
			log.error("内部认证服务异常: {}", ex.getMessage(), ex);
			throw ex;
		}
		catch (Exception ex) {
			log.error("检索用户时发生异常: {}", ex.getMessage(), ex);
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
		}
	}

	/**
	 * 创建认证成功后的Authentication对象
	 * @param principal 认证主体
	 * @param authentication 认证信息
	 * @param user 用户详情
	 * @return 认证成功后的Authentication对象
	 */
	@Override
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
			UserDetails user) {
		boolean upgradeEncoding = this.userDetailsPasswordService != null
				&& this.passwordEncoder.upgradeEncoding(user.getPassword());
		if (upgradeEncoding) {
			String presentedPassword = authentication.getCredentials().toString();
			String newPassword = this.passwordEncoder.encode(presentedPassword);
			user = this.userDetailsPasswordService.updatePassword(user, newPassword);
		}
		return super.createSuccessAuthentication(principal, authentication, user);
	}

	/**
	 * 准备定时攻击保护，如果未找到用户编码密码为空则进行编码
	 */
	private void prepareTimingAttackProtection() {
		if (this.userNotFoundEncodedPassword == null) {
			this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
		}
	}

	/**
	 * 防止时序攻击的缓解措施
	 * @param authentication 用户名密码认证令牌
	 */
	private void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
		if (authentication.getCredentials() != null) {
			String presentedPassword = authentication.getCredentials().toString();
			this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
		}
	}

	/**
	 * 设置用于编码和验证密码的PasswordEncoder实例
	 * @param passwordEncoder 密码编码器实例，不能为null
	 */
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
		this.passwordEncoder = passwordEncoder;
		this.userNotFoundEncodedPassword = null;
	}

	protected PasswordEncoder getPasswordEncoder() {
		return this.passwordEncoder;
	}


	protected UserDetailsService getUserDetailsService() {
		return this.userDetailsService;
	}


}
