
package com.example.admin.api.feign;

import com.example.admin.api.dto.UserDTO;
import com.example.admin.api.dto.UserInfo;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import com.example.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

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
	Result<UserInfo> info(@SpringQueryMap UserDTO user);

}
