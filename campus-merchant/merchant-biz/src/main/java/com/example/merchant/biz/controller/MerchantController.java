package com.example.merchant.biz.controller;

import com.example.common.core.util.Result;
import com.example.merchant.api.dto.MerchantQualificationApplyDTO;
import com.example.merchant.api.feign.RemoteMchUserService;
import com.example.merchant.biz.service.MerchantService;
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

    @PostMapping("/apply/{id}")
    @Operation(summary = "商家资质申请",
    description = "用于申请商家资质")
    public Result<Void> applyForMchVerification(@PathVariable Long id, @Valid @RequestBody MerchantQualificationApplyDTO dto){
        return merchantService.applyForMchVerification(id,dto);
    }
}
