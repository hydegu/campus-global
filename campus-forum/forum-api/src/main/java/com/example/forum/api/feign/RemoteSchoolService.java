package com.example.forum.api.feign;

import com.example.admin.api.vo.SysSchoolVO;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import com.example.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 学校服务Feign客户端
@FeignClient(contextId = "remoteSchoolService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteSchoolService {

	@NoToken
	@GetMapping("/api/school/{id}")
	Result<SysSchoolVO> getSchoolById(@PathVariable("id") Long id);
}
