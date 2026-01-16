
package com.example.admin.api.feign;

import com.example.admin.api.entity.SysOauthClientDetails;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import com.example.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 客户端详情服务接口
 */
@FeignClient(contextId = "remoteClientDetailsService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteClientDetailsService {

	/**
	 * 通过clientId 查询客户端信息 (未登录，需要无token 内部调用)
	 * @param clientId 用户名
	 * @return R
	 */
	@NoToken
	@GetMapping("/client/getClientDetailsById/{clientId}")
	Result<SysOauthClientDetails> getClientDetailsById(@PathVariable("clientId") String clientId);

}
