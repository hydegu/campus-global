package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.AuditRecordQueryDTO;
import com.example.admin.api.dto.CreateAuditRecordDTO;
import com.example.admin.api.vo.AuditRecordVO;
import com.example.admin.biz.service.AuditService;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import com.example.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audit-record")
@RequiredArgsConstructor
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "审核记录管理", description = "审核记录的新增和查询功能")
public class AuditRecordController {

	private final AuditService auditService;

	@PostMapping
	@Inner
	@Operation(summary = "创建审核记录", description = "创建新的审核记录，需要提供业务类型和申请人ID。系统会自动生成审核编号，默认状态为待审核。需要权限：audit:record:add")
	public Result<Long> createAuditRecord(@Valid @RequestBody CreateAuditRecordDTO dto) {
		Long auditRecordId = auditService.createAuditRecord(dto);
		return Result.ok(auditRecordId);
	}

	@GetMapping("/{id}")
	@Operation(summary = "根据ID查询审核记录", description = "根据审核记录ID查询审核记录的详细信息。需要权限：audit:record:query")
	public Result<AuditRecordVO> getAuditRecordById(
			@Parameter(description = "审核记录ID", required = true)
			@PathVariable Long id) {
		AuditRecordVO auditRecordVO = auditService.getAuditRecordById(id);
		return Result.ok(auditRecordVO);
	}

	@GetMapping("/list")
	@Operation(summary = "分页查询审核记录", description = "分页查询审核记录列表，支持按审核状态和业务类型筛选。需要权限：audit:record:query")
	public Result<Page<AuditRecordVO>> listAuditRecords(
			@ParameterObject @Valid AuditRecordQueryDTO queryDTO) {
		Page<AuditRecordVO> page = auditService.listAuditRecords(queryDTO);
		return Result.ok(page);
	}

	@PutMapping("/{id}/audit")
	@Operation(summary = "审核", description = "通过审核记录ID审核，支持所有业务类型（商家入驻、合伙人申请、服务人员申请、骑手申请、提现、商品上架）。需要权限：audit:record:audit")
	public Result<Void> auditByRecordId(
			@Parameter(description = "审核记录ID", required = true)
			@PathVariable Long id,
			@Valid @RequestBody com.example.admin.api.dto.AuditDTO auditDTO) {
		auditService.auditByRecordId(id, auditDTO);
		return Result.ok();
	}
}