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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/apply")
    @Operation(summary = "商家资质申请",
    description = "用于申请商家资质，会在申请后自动创建用户，默认密码可传，默认为手机号后6位")
    public Result<Void> applyForMchVerification(@Valid @RequestBody MerchantQualificationApplyDTO dto){
        merchantService.applyForMchVerification(dto);
        return merchantService.applyForMchVerification(dto);
    }
}
