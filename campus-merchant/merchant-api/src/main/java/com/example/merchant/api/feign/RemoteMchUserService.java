
package com.example.merchant.api.feign;

import com.example.admin.api.dto.CreateAuditRecordDTO;
import com.example.admin.api.dto.CreateMchUserDTO;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.api.vo.UserMchListVO;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 商家用户服务接口 用于调用商家用户创建
 */
@FeignClient(contextId = "remoteMchUserService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteMchUserService {

	@PostMapping("/api/user/mch-user/create")
	public Result<UserMchListVO> createMchUser(@Valid @RequestBody CreateMchUserDTO dto);

	@PostMapping("/api/audit-record")
	public Result<Long> createAuditRecord(@Valid @RequestBody CreateAuditRecordDTO dto);
}
