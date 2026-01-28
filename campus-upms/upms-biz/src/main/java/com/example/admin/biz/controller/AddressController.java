package com.example.admin.biz.controller;

import com.example.admin.api.entity.Address;
import com.example.admin.biz.service.AddressService;
import com.example.common.core.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 地址管理Controller
 */
@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
@Tag(name = "地址管理", description = "地址查询接口")
public class AddressController {

	private final AddressService addressService;

	@GetMapping("/coordinate/{addressId}")
	@Operation(summary = "获取地址经纬度", description = "根据地址ID获取地址的经纬度坐标")
	public Result<String> getAddressCoordinate(
			@Parameter(description = "地址ID", required = true) @PathVariable Long addressId) {
		String coordinate = addressService.getAddressCoordinate(addressId);
		return Result.ok(coordinate);
	}

	@GetMapping("/merchant/{merchantId}")
	@Operation(summary = "获取商家地址经纬度", description = "根据商家ID获取商家地址的经纬度坐标")
	public Result<String> getMerchantAddressCoordinate(
			@Parameter(description = "商家ID", required = true) @PathVariable Long merchantId) {
		String coordinate = addressService.getMerchantAddressCoordinate(merchantId);
		return Result.ok(coordinate);
	}
}