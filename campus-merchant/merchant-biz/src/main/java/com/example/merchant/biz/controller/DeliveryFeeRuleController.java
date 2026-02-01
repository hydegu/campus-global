package com.example.merchant.biz.controller;

import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleAddDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleQueryDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleStatusDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleUpdateDTO;
import com.example.merchant.api.vo.DeliveryFeeRuleVO;
import com.example.merchant.biz.service.DeliveryFeeRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 配送费规则管理
 *
 * @author campus-merchant
 */
@RestController
@RequestMapping("/api/delivery/rule")
@RequiredArgsConstructor
@Validated
@Tag(name = "配送费规则管理", description = "配送费规则的增删改查功能")
public class DeliveryFeeRuleController {

	private final DeliveryFeeRuleService deliveryFeeRuleService;

	@GetMapping("/list")
	@Operation(summary = "分页查询配送费规则列表", description = "根据查询条件分页查询配送费规则列表，支持按配置ID、规则类型、时段类型等条件筛选。")
	public Result<PageResult<DeliveryFeeRuleVO>> listRules(@ParameterObject DeliveryFeeRuleQueryDTO queryDTO) {
		PageResult<DeliveryFeeRuleVO> pageResult = deliveryFeeRuleService.listRules(queryDTO);
		return Result.ok(pageResult);
	}

	@GetMapping("/{id}")
	@Operation(summary = "查询配送费规则详情", description = "通过规则ID查询配送费规则详细信息，包括规则类型、距离/时间规则参数等。")
	public Result<DeliveryFeeRuleVO> getRuleDetail(@PathVariable Integer id) {
		DeliveryFeeRuleVO vo = deliveryFeeRuleService.getRuleDetail(id);
		return Result.ok(vo);
	}

	@PostMapping
	@Operation(summary = "新增配送费规则", description = "创建新的配送费规则。规则类型包括距离规则和时间规则，需要根据类型填写相应字段。")
	public Result<Void> addRule(@Validated @RequestBody DeliveryFeeRuleAddDTO dto) {
		deliveryFeeRuleService.addRule(dto);
		return Result.ok();
	}

	@PutMapping("/{id}")
	@Operation(summary = "更新配送费规则", description = "更新指定的配送费规则信息。可更新规则类型及相应的距离/时间规则参数。")
	public Result<Void> updateRule(@PathVariable Integer id, @Validated @RequestBody DeliveryFeeRuleUpdateDTO dto) {
		deliveryFeeRuleService.updateRule(id, dto);
		return Result.ok();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除配送费规则", description = "软删除指定的配送费规则。")
	public Result<Void> deleteRule(@PathVariable Integer id) {
		deliveryFeeRuleService.deleteRule(id);
		return Result.ok();
	}

	@PutMapping("/{id}/status")
	@Operation(summary = "修改规则状态", description = "启用或禁用指定的配送费规则。status=1启用，status=0禁用（软删除）。")
	public Result<Void> updateRuleStatus(@PathVariable Integer id,
			@Validated @RequestBody DeliveryFeeRuleStatusDTO statusDTO) {
		deliveryFeeRuleService.updateRuleStatus(id, statusDTO);
		return Result.ok();
	}

	@GetMapping("/config/{configId}")
	@Operation(summary = "根据配置ID查询规则列表", description = "查询指定配送费配置下的所有规则列表，按排序字段升序排列。")
	public Result<List<DeliveryFeeRuleVO>> listRulesByConfigId(@PathVariable Long configId) {
		List<DeliveryFeeRuleVO> rules = deliveryFeeRuleService.listRulesByConfigId(configId);
		return Result.ok(rules);
	}

}