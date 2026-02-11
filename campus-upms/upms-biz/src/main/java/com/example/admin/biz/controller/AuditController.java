package com.example.admin.biz.controller;

import com.example.admin.api.dto.CreateAuditDTO;
import com.example.admin.api.entity.AuditRecord;
import com.example.admin.biz.service.AuditService;
import com.example.common.core.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
@Tag(name = "审核管理", description = "审核记录的创建和查询功能")
@Validated
public class AuditController {

	private final AuditService auditService;

	@PostMapping("/create")
	@Operation(summary = "创建审核记录", description = "根据业务类型和申请人ID创建一条新的审核记录")
	public Result<AuditRecord> createAuditRecord(@RequestBody CreateAuditDTO dto) {
		AuditRecord auditRecord = auditService.createAuditRecord(dto);
		return Result.ok(auditRecord);
	}
}
