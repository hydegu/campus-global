
package com.example.admin.api.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import com.example.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 令牌服务接口
 */
@FeignClient(contextId = "remoteTokenService", value = ServiceNameConstants.AUTH_SERVICE)
public interface RemoteTokenService {

	/**
	 * 分页查询token 信息
	 * @param params 分页参数
	 * @return page
	 */
	@NoToken
	@PostMapping("/token/page")
    Result<Page> getTokenPage(@RequestBody Map<String, Object> params);

	/**
	 * 根据token删除token信息
	 * @param token 要删除的token
	 * @return 删除操作结果，包含是否成功的布尔值
	 */
	@NoToken
	@DeleteMapping("/token/remove/{token}")
    Result<Boolean> removeTokenById(@PathVariable("token") String token);

	/**
	 * 根据令牌查询用户信息
	 * @param token 用户令牌
	 * @return 包含用户信息的响应结果
	 */
	@NoToken
	@GetMapping("/token/query-token")
    Result<Map<String, Object>> queryToken(@RequestParam("token") String token);

}
