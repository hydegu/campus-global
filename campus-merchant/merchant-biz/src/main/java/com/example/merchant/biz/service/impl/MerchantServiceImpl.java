package com.example.merchant.biz.service.impl;

import com.example.admin.api.dto.CreateAuditRecordDTO;
import com.example.admin.api.dto.UpdateUserMchDTO;
import com.example.admin.api.feign.RemoteUserService;
import com.example.common.core.enums.BizType;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.util.Result;
import com.example.merchant.api.dto.MerchantQualificationApplyDTO;
import com.example.merchant.biz.service.MerchantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final RemoteUserService remoteUserService;

    @Override
    public Result<Void> applyForMchVerification(Long id,MerchantQualificationApplyDTO dto){
        UpdateUserMchDTO updateDTO = new UpdateUserMchDTO();

        // ========== 必填字段 ==========
        updateDTO.setPhone(dto.getContact()) // 手机号：联系方式
                 .setShopName(dto.getMerchantName()) // 商户名：商家名称
                 .setPartnerId(dto.getPartnerId()) // 合伙人ID
                 .setPaymentAccount(dto.getPayoutAccount()) // 银行卡号：打款账户
                 .setBusinessLicenseUrls(dto.getBusinessLicenseURLs()) // 营业执照URL
                 .setContactName(dto.getContactName()) // 联系人姓名
                 .setLogo(dto.getLogo()) // 商户logo
                 .setMinimumOrderAmount(dto.getMinimumOrderAmount()); // 最低起送金额

        // 先修改商家用户
        Result<Void> updateResult = remoteUserService.updateUser(id,3,updateDTO);

        if(updateResult.getCode() != 200) throw new BusinessException("FAILD_UPDATE","修改商家失败");

        // 调用审核表创建方法创建对应审核数据
        CreateAuditRecordDTO createAuditRecordDTO = CreateAuditRecordDTO
                .builder()
                .bizType(BizType.MERCHANT_SETTLE.getCode())
                .applicantId(id)
                .build();
        remoteUserService.createAuditRecord(createAuditRecordDTO);

        return Result.ok();
    }
}
