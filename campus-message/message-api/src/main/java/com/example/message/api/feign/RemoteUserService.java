package com.example.message.api.feign;

import com.example.admin.api.dto.UserInfo;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import com.example.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteUserService {

	@NoToken
	@GetMapping("/api/user/{id}/info")
	Result<UserInfo> getUserInfoById(@PathVariable("id") Long id);
}
