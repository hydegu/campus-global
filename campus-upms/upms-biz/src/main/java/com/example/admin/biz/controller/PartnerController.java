package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.biz.dto.AuditDTO;
import com.example.admin.biz.dto.PartnerAuditQueryDTO;
import com.example.admin.biz.service.AuditService;
import com.example.admin.biz.vo.PartnerAuditVO;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpHeaders;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys/partner")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "合伙人管理", description = "合伙人的增删改查及审核功能")
public class PartnerController {

	private final AuditService auditService;

	@GetMapping("/audit/list")
	@Operation(summary = "分页查询合伙人审核列表", description = "根据查询条件分页查询合伙人审核列表，支持按合伙人姓名、审核状态等条件筛选")
	public Result<Page<PartnerAuditVO>> listPartnerAudit(@Valid @ParameterObject PartnerAuditQueryDTO queryDTO) {
		Page<PartnerAuditVO> page = auditService.listPartnerAudit(queryDTO);
		return Result.ok(page);
	}

	@PostMapping("/{id}/approve")
	@Operation(summary = "审批通过合伙人申请", description = "审核通过合伙人申请，可附带审核意见")
	public Result<Void> approvePartner(@PathVariable Long id, @Valid @RequestBody AuditDTO auditDTO) {
		auditDTO.setAuditStatus(1);
		auditService.auditPartner(id, auditDTO);
		return Result.ok();
	}

	@PostMapping("/{id}/reject")
	@Operation(summary = "审批拒绝合伙人申请", description = "审核拒绝合伙人申请，需填写拒绝原因")
	public Result<Void> rejectPartner(@PathVariable Long id, @Valid @RequestBody AuditDTO auditDTO) {
		auditDTO.setAuditStatus(2);
		auditService.auditPartner(id, auditDTO);
		return Result.ok();
	}
}
