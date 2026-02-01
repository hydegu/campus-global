package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.PartnerAuditQueryDTO;
import com.example.admin.biz.service.AuditService;
import com.example.admin.api.vo.PartnerAuditVO;
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
}
