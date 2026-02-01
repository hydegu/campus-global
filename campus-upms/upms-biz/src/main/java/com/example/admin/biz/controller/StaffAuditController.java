package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.ServiceStaffAuditQueryDTO;
import com.example.admin.biz.service.AuditService;
import com.example.admin.api.vo.ServiceStaffAuditVO;
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
@RequestMapping("/api/audit/staff")
@RequiredArgsConstructor
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "服务人员审核", description = "管理员对服务人员申请进行审核功能")
public class StaffAuditController {

	private final AuditService auditService;

	@GetMapping("/list")
	@Operation(summary = "分页查询服务人员审核列表", description = "根据查询条件分页查询服务人员审核列表，支持按审核状态等条件筛选")
	public Result<Page<ServiceStaffAuditVO>> listServiceStaffAudit(@Valid @ParameterObject ServiceStaffAuditQueryDTO queryDTO) {
		Page<ServiceStaffAuditVO> page = auditService.listServiceStaffAudit(queryDTO);
		return Result.ok(page);
	}
}
