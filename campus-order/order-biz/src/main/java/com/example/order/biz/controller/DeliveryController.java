package com.example.order.biz.controller;

import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import com.example.order.api.dto.DeliveryCalculateDTO;
import com.example.order.api.vo.DeliveryCalculateVO;
import com.example.order.biz.service.DeliveryFeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "配送管理", description = "配送费计算、预计送达时间查询等功能")
public class DeliveryController {

	private final DeliveryFeeService deliveryFeeService;

	@PostMapping("/calculate")
	@Operation(summary = "计算配送费和预计送达时间", description = "根据商家、收货地址、商品金额计算配送费和预计送达时间")
	public Result<DeliveryCalculateVO> calculateDeliveryFee(@Valid @RequestBody DeliveryCalculateDTO calculateDTO) {
		DeliveryCalculateVO vo = deliveryFeeService.calculateDeliveryFee(calculateDTO);
		return Result.ok(vo);
	}
}
