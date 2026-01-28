
package com.example.merchant.api.feign;

import com.example.admin.api.dto.CreateAuditRecordDTO;
import com.example.admin.api.dto.CreateMchUserDTO;
import com.example.admin.api.dto.MchInfoDTO;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.api.vo.UserMchListVO;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商家用户服务接口 用于调用商家用户创建
 */
@FeignClient(contextId = "remoteMchUserService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteMchUserService {

	@PutMapping("/api/user/{id}")
	public Result<Void> updateUser(@PathVariable Long id, @RequestParam Integer userType, @Valid @RequestBody Object updateDTO);

	@PostMapping("/api/audit-record")
	public Result<Long> createAuditRecord(@Valid @RequestBody CreateAuditRecordDTO dto);

	/**
	 * 根据商家用户ID获取商家信息
	 *
	 * @param baseUserId 商家用户ID
	 * @return 商家信息
	 */
	@GetMapping("/api/mch/{baseUserId}")
	public Result<MchInfoDTO> getMchInfoByBaseUserId(@PathVariable Long baseUserId);

	/**
	 * 批量获取商家信息
	 *
	 * @param baseUserIds 商家用户ID列表
	 * @return 商家信息列表
	 */
	@PostMapping("/api/mch/batch")
	public Result<List<MchInfoDTO>> batchGetMchInfo(@RequestBody List<Long> baseUserIds);
}
