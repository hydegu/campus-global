package com.example.merchant.biz.controller;

import com.example.common.core.util.Result;
import com.example.common.security.annotation.Inner;
import com.example.merchant.api.dto.MerchantQualificationApplyDTO;
import com.example.merchant.biz.service.DeliveryFeeCalculateService;
import com.example.merchant.biz.service.MerchantService;
import com.example.order.api.dto.DeliveryCalculateDTO;
import com.example.order.api.vo.DeliveryCalculateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品家资质申请
 *
 * @author campus-merchant
 */
@RestController
@RequestMapping("/api/merchant")
@RequiredArgsConstructor
@Validated
@Tag(name = "商品家资质申请")
public class MerchantController {

    private final MerchantService merchantService;
    private final DeliveryFeeCalculateService deliveryFeeCalculateService;

    @PostMapping("/apply/{id}")
    @Operation(summary = "商家资质申请",
    description = "用于申请商家资质")
    public Result<Void> applyForMchVerification(@PathVariable Long id, @Valid @RequestBody MerchantQualificationApplyDTO dto){
        return merchantService.applyForMchVerification(id,dto);
    }

    @PostMapping("/delivery/calculate")
    @Inner
    @Operation(summary = "计算配送费", description = "根据商家ID和收货地址计算配送费")
    public Result<DeliveryCalculateVO> calculateDeliveryFee(@Valid @RequestBody DeliveryCalculateDTO calculateDTO) {
        DeliveryCalculateVO vo = deliveryFeeCalculateService.calculateDeliveryFee(calculateDTO);
        return Result.ok(vo);
    }
}
