package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.RiderApplyQueryDTO;
import com.example.admin.biz.service.AuditService;
import com.example.admin.api.vo.RiderApplyVO;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rider/apply")
@RequiredArgsConstructor
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "骑手申请审核", description = "骑手申请记录的查询和审核功能")
public class RiderApplyController {

	private final AuditService auditService;

	@GetMapping("/list")
	@Operation(summary = "分页查询骑手申请列表", description = "根据查询条件分页查询骑手申请列表，支持按骑手姓名、手机号、审核状态等条件筛选。返回包含骑手信息、审核状态、申请时间等的分页结果。审核状态：0-待审核，1-已通过，2-已拒绝")
	public Result<Page<RiderApplyVO>> listRiderApply(@Valid @ParameterObject RiderApplyQueryDTO queryDTO) {
		Page<RiderApplyVO> page = auditService.listRiderApply(queryDTO);
		return Result.ok(page);
	}
}
