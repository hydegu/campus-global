package com.example.merchant.biz.controller;

import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigAddDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigBatchAddDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigQueryDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigStatusDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigUpdateDTO;
import com.example.merchant.api.vo.DeliveryFeeConfigVO;
import com.example.merchant.biz.service.DeliveryFeeConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 配送费配置管理
 *
 * @author campus-merchant
 */
@RestController
@RequestMapping("/api/delivery/config")
@RequiredArgsConstructor
@Validated
@Tag(name = "配送费配置管理", description = "配送费配置的增删改查功能")
public class DeliveryFeeConfigController {

	private final DeliveryFeeConfigService deliveryFeeConfigService;

	@GetMapping("/list")
	@Operation(summary = "分页查询配送费配置列表", description = "根据查询条件分页查询配送费配置列表，支持按配置名称、状态等条件筛选。返回包含配置及其关联规则列表的分页结果。")
	public Result<PageResult<DeliveryFeeConfigVO>> listConfigs(@ParameterObject DeliveryFeeConfigQueryDTO queryDTO) {
		PageResult<DeliveryFeeConfigVO> pageResult = deliveryFeeConfigService.listConfigs(queryDTO);
		return Result.ok(pageResult);
	}

	@GetMapping("/{id}")
	@Operation(summary = "查询配送费配置详情", description = "通过配置ID查询配送费配置详细信息，包括配置名称、起步价、起步距离、状态等信息，以及关联的配送费规则列表。")
	public Result<DeliveryFeeConfigVO> getConfigDetail(@PathVariable Long id) {
		DeliveryFeeConfigVO vo = deliveryFeeConfigService.getConfigDetail(id);
		return Result.ok(vo);
	}

	@PostMapping
	@Operation(summary = "新增配送费配置", description = "创建新的配送费配置。必填字段：配置名称、起步价、起步距离。status默认为1（启用）。")
	public Result<Void> addConfig(@Validated @RequestBody DeliveryFeeConfigAddDTO dto) {
		deliveryFeeConfigService.addConfig(dto);
		return Result.ok();
	}

	@PutMapping("/{id}")
	@Operation(summary = "更新配送费配置", description = "更新指定的配送费配置信息。可更新字段：配置名称、起步价、起步距离、状态。")
	public Result<Void> updateConfig(@PathVariable Long id, @Validated @RequestBody DeliveryFeeConfigUpdateDTO dto) {
		deliveryFeeConfigService.updateConfig(id, dto);
		return Result.ok();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除配送费配置", description = "软删除指定的配送费配置。如果配置下有关联的配送费规则，则不允许删除。")
	public Result<Void> deleteConfig(@PathVariable Long id) {
		deliveryFeeConfigService.deleteConfig(id);
		return Result.ok();
	}

	@PostMapping("/batch")
	@Operation(summary = "批量新增配送费配置", description = "批量创建配送费配置。所有配置将作为一个事务执行，全部成功或全部失败。适用于需要批量导入配置的场景。")
	public Result<Void> batchAddConfigs(@Validated @RequestBody DeliveryFeeConfigBatchAddDTO dto) {
		deliveryFeeConfigService.batchAddConfigs(dto);
		return Result.ok();
	}

	@PutMapping("/{id}/status")
	@Operation(summary = "修改配置状态", description = "启用或禁用指定的配送费配置。status=1启用，status=0禁用。")
	public Result<Void> updateConfigStatus(@PathVariable Long id,
			@Validated @RequestBody DeliveryFeeConfigStatusDTO statusDTO) {
		deliveryFeeConfigService.updateConfigStatus(id, statusDTO);
		return Result.ok();
	}

}