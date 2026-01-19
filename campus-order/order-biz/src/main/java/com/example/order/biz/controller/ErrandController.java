package com.example.order.biz.controller;

import com.alibaba.nacos.api.model.v2.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.order.api.dto.ErrandAcceptDTO;
import com.example.order.api.dto.ErrandCreateDTO;
import com.example.order.api.dto.ErrandDeliverDTO;
import com.example.order.api.dto.ErrandPickupDTO;
import com.example.order.api.dto.ErrandQueryDTO;
import com.example.order.api.vo.ErrandDetailVO;
import com.example.order.api.vo.ErrandListVO;
import com.example.order.biz.service.ErrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/errand")
@RequiredArgsConstructor
@Tag(name = "服务订单管理", description = "服务订单（跑腿订单）管理接口")
public class ErrandController {

	private final ErrandService errandService;

	@PostMapping("/create")
	@Operation(summary = "创建服务订单", description = "用户创建服务订单")
	public Result<Long> createErrand(@Valid @RequestBody ErrandCreateDTO createDTO) {
		log.info("创建服务订单请求，参数：{}", createDTO);
		Long orderId = errandService.createErrand(createDTO);
		return Result.success(orderId);
	}

	@PostMapping("/accept")
	@Operation(summary = "服务人员接单", description = "服务人员接单")
	public Result<Void> acceptErrand(@Valid @RequestBody ErrandAcceptDTO acceptDTO) {
		log.info("服务人员接单请求，参数：{}", acceptDTO);
		errandService.acceptErrand(acceptDTO);
		return Result.success();
	}

	@PostMapping("/pickup")
	@Operation(summary = "服务人员取货", description = "服务人员取货")
	public Result<Void> pickupErrand(@Valid @RequestBody ErrandPickupDTO pickupDTO) {
		log.info("服务人员取货请求，参数：{}", pickupDTO);
		errandService.pickupErrand(pickupDTO);
		return Result.success();
	}

	@PostMapping("/deliver")
	@Operation(summary = "服务人员送达", description = "服务人员送达")
	public Result<Void> deliverErrand(@Valid @RequestBody ErrandDeliverDTO deliverDTO) {
		log.info("服务人员送达请求，参数：{}", deliverDTO);
		errandService.deliverErrand(deliverDTO);
		return Result.success();
	}

	@PostMapping("/cancel")
	@Operation(summary = "取消订单", description = "取消服务订单")
	public Result<Void> cancelErrand(
			@Parameter(description = "订单ID", required = true) @RequestParam Long orderId,
			@Parameter(description = "取消类型:1-用户取消 2-商家取消 3-超时取消", required = true) @RequestParam Integer cancelType) {
		log.info("取消服务订单请求，订单ID：{}，取消类型：{}", orderId, cancelType);
		errandService.cancelErrand(orderId, cancelType);
		return Result.success();
	}

	@GetMapping("/list")
	@Operation(summary = "服务订单列表", description = "查询服务订单列表")
	public Result<Page<ErrandListVO>> listErrands(ErrandQueryDTO queryDTO) {
		log.info("查询服务订单列表请求，参数：{}", queryDTO);
		Page<ErrandListVO> page = errandService.listErrands(queryDTO);
		return Result.success(page);
	}

	@GetMapping("/{id}")
	@Operation(summary = "服务订单详情", description = "获取服务订单详情")
	public Result<ErrandDetailVO> getErrandDetail(
			@Parameter(description = "订单ID", required = true) @PathVariable Long id) {
		log.info("查询服务订单详情请求，订单ID：{}", id);
		ErrandDetailVO detail = errandService.getErrandDetail(id);
		return Result.success(detail);
	}
}
