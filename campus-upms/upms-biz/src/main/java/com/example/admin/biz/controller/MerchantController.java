package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.MerchantSettleInQueryDTO;
import com.example.admin.api.vo.MerchantSettleInVO;
import com.example.admin.biz.service.AuditService;
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
@RequestMapping("/api/merchant/settle-in")
@RequiredArgsConstructor
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
}
