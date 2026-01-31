
package com.example.admin.api.feign;

import com.example.admin.api.dto.MerchantBalanceUpdateDTO;
import com.example.admin.api.dto.UserDTO;
import com.example.admin.api.dto.UserInfo;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import com.example.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 远程用户服务接口：提供用户信息查询功能
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteUserService {

	/**
	 * 通过用户名查询用户、角色信息
	 * @param user 用户查询对象
	 * @return Result<UserInfo> 用户信息响应对象
	 */
	@NoToken
	@GetMapping("/user/info/query")
	Result<UserInfo> getUserInfo(@SpringQueryMap UserDTO user);

	/**
	 * 通过用户ID查询用户信息
	 * @param id 用户ID
	 * @return Result<UserInfo> 用户信息响应对象
	 */
	@NoToken
	@GetMapping("/api/user/{id}/info")
	Result<UserInfo> getUserInfoById(@PathVariable("id") Long id);

	/**
	 * 更新用户余额或累计总收入（支持商家/骑手/合伙人）
	 * @param dto 用户余额更新DTO
	 * @return 操作结果
	 */
	@NoToken
	@PostMapping("/api/user/balance/update")
	Result<Void> updateUserBalance(@RequestBody MerchantBalanceUpdateDTO dto);

}
