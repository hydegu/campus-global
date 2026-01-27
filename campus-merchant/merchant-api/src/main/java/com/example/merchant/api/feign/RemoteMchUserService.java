
package com.example.merchant.api.feign;

import com.example.admin.api.dto.UserInfo;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商家用户服务接口 用于调用商家用户查询/创建/编辑
 */
@FeignClient(contextId = "remoteMchUserService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteMchUserService {

	@GetMapping("/api/user/{id}/info")
	public Result<UserInfo> getUserInfoById(@PathVariable Long id);

}
