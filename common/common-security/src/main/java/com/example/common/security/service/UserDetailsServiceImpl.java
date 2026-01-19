
package com.example.common.security.service;

import com.example.admin.api.dto.UserDTO;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.api.feign.RemoteUserService;
import com.example.common.core.constant.CacheConstants;
import com.example.common.core.util.Result;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详情服务实现类，提供基于用户名加载用户详情功能
 */
@Slf4j
@Primary
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final RemoteUserService remoteUserService;

	private final CacheManager cacheManager;

	/**
	 * 根据用户名加载用户详情
	 * @param username 用户名
	 * @return 用户详情信息
	 * @throws Exception 获取用户信息过程中可能抛出的异常
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		log.info("=== 开始加载用户详情 ===");
		log.info("用户名: {}", username);
		
		Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
		if (cache != null && cache.get(username) != null) {
			UserDetails cachedUserDetails = (ExtraUser) cache.get(username).get();
			log.info("从缓存中获取到用户详情");
			log.info("缓存用户ID: {}", ((ExtraUser) cachedUserDetails).getId());
			log.info("缓存用户名: {}", cachedUserDetails.getUsername());
			log.info("缓存用户手机号: {}", ((ExtraUser) cachedUserDetails).getPhone());
			return cachedUserDetails;
		}
		
		log.info("缓存中未找到用户，开始从远程服务获取");

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		log.info("调用远程服务获取用户信息，参数: {}", userDTO);
		
		Result<UserInfo> result = remoteUserService.getUserInfo(userDTO);
		log.info("远程服务返回结果: {}", result);
		
		if (result.getCode() != 0) {
			log.error("远程服务返回错误，code: {}, msg: {}", result.getCode(), result.getMsg());
		}
		
		UserDetails userDetails = getUserDetails(result);
		
		log.info("用户详情加载完成");
		if (userDetails instanceof ExtraUser) {
			ExtraUser extraUser = (ExtraUser) userDetails;
			log.info("用户ID: {}", extraUser.getId());
			log.info("用户名: {}", extraUser.getUsername());
			log.info("用户手机号: {}", extraUser.getPhone());
			log.info("用户状态 - enabled: {}, accountNonExpired: {}, credentialsNonExpired: {}, accountNonLocked: {}", 
				extraUser.isEnabled(), extraUser.isAccountNonExpired(), 
				extraUser.isCredentialsNonExpired(), extraUser.isAccountNonLocked());
		}
		
		if (cache != null) {
			cache.put(username, userDetails);
			log.info("用户详情已缓存");
		}
		return userDetails;
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}
