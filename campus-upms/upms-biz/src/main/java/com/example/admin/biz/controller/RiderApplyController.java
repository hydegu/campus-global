package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.biz.dto.AuditDTO;
import com.example.admin.biz.dto.RiderApplyQueryDTO;
import com.example.admin.biz.service.AuditService;
import com.example.admin.biz.vo.RiderApplyVO;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rider/apply")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "骑手申请审核", description = "骑手申请记录的查询和审核功能")
public class RiderApplyController {

	private final AuditService auditService;

	@GetMapping("/list")
	@Operation(summary = "分页查询骑手申请列表", description = "根据查询条件分页查询骑手申请列表，支持按骑手姓名、手机号、审核状态等条件筛选。返回包含骑手信息、审核状态、申请时间等的分页结果。审核状态：0-待审核，1-已通过，2-已拒绝")
	public Result<Page<RiderApplyVO>> listRiderApply(@Valid RiderApplyQueryDTO queryDTO) {
		Page<RiderApplyVO> page = auditService.listRiderApply(queryDTO);
		return Result.ok(page);
	}

	@PutMapping("/approve/{id}")
	@Operation(summary = "审核通过骑手申请", description = "根据申请记录ID审核通过骑手申请，更新审核状态为已通过。业务逻辑：1.验证申请记录存在且审核状态为\"待审核\"（避免重复审核） 2.更新audit_record状态为\"审核通过\"，记录审核人和审核时间")
	public Result<Void> approveRiderApply(@PathVariable Long id, @Valid @RequestBody AuditDTO auditDTO) {
		auditDTO.setAuditStatus(1);
		auditService.auditRider(id, auditDTO);
		return Result.ok();
	}

	@PutMapping("/reject/{id}")
	@Operation(summary = "审核拒绝骑手申请", description = "根据申请记录ID审核拒绝骑手申请，更新审核状态为已拒绝。业务逻辑：1.验证申请记录存在且审核状态为\"待审核\"（避免重复审核） 2.更新audit_record状态为\"审核拒绝\"，记录审核人、审核时间和拒绝理由")
	public Result<Void> rejectRiderApply(@PathVariable Long id, @Valid @RequestBody AuditDTO auditDTO) {
		auditDTO.setAuditStatus(2);
		auditService.auditRider(id, auditDTO);
		return Result.ok();
	}
}
