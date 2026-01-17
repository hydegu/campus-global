package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.biz.dto.AuditDTO;
import com.example.admin.biz.dto.MerchantSettleInQueryDTO;
import com.example.admin.biz.service.AuditService;
import com.example.admin.biz.vo.MerchantSettleInVO;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant/settle-in")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "商家入驻管理", description = "商家入驻申请的提交、查询、编辑、删除、审核等功能")
public class MerchantController {

	private final AuditService auditService;

	@GetMapping("/list")
	@Operation(summary = "分页查询商家入驻申请列表", description = "根据查询条件分页查询商家入驻申请列表，支持按商家名称、审核状态、所属合伙人ID等条件筛选。返回包含合伙人、结算账户、审核记录等关联信息的分页结果")
	public Result<Page<MerchantSettleInVO>> listMerchantSettleIn(@Valid @ParameterObject MerchantSettleInQueryDTO queryDTO) {
		Page<MerchantSettleInVO> page = auditService.listMerchantSettleIn(queryDTO);
		return Result.ok(page);
	}

	@PostMapping("/{id}/audit")
	@Operation(summary = "审核商家入驻申请", description = "审核指定的商家入驻申请。必填字段：审核结果（1-通过，2-拒绝）；可选字段：审核意见（最多500字符）。业务逻辑：1. 验证申请是否存在且关联了审核记录 2. 状态校验：只能审核待审核状态（audit_status = 0）的申请 3. 获取当前登录用户作为审核人（使用SecurityUtils） 4. 更新审核记录：设置审核状态、审核意见、审核时间、审核人信息 5. 更新商家状态：- 审核通过（audit_status = 1）→ merchant.status = 1（启用） - 审核拒绝（audit_status = 2）→ merchant.status = 0（禁用）")
	public Result<Void> auditMerchant(@PathVariable Long id, @Valid @RequestBody AuditDTO auditDTO) {
		auditService.auditMerchant(id, auditDTO);
		return Result.ok();
	}
}
