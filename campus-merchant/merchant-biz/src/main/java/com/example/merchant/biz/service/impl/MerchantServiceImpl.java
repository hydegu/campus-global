package com.example.merchant.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.admin.api.dto.AuditDTO;
import com.example.admin.api.dto.CreateAuditRecordDTO;
import com.example.admin.api.dto.CreateMchUserDTO;
import com.example.admin.api.vo.UserMchListVO;
import com.example.common.core.enums.BizType;
import com.example.common.core.util.Result;
import com.example.merchant.api.dto.MerchantQualificationApplyDTO;
import com.example.merchant.api.feign.RemoteMchUserService;
import com.example.merchant.biz.service.MerchantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final RemoteMchUserService remoteMchUserService;

    @Override
    public Result<Void> applyForMchVerification(MerchantQualificationApplyDTO dto){
        CreateMchUserDTO createDTO = new CreateMchUserDTO();

        // ========== 必填字段 ==========
        createDTO.setUsername(dto.getContact()); // 用户名：使用联系方式作为用户名
        createDTO.setPassword(StrUtil.subSufByLength(dto.getContact(), 6)); // 密码：手机号后6位（TODO: 不够安全）
        createDTO.setPhone(dto.getContact()); // 手机号：联系方式
        createDTO.setMchName(dto.getMerchantName()); // 商户名：商家名称

        // ========== 可选字段 ==========
        createDTO.setEmail(dto.getEmail()); // 邮箱
        createDTO.setPartnerId(dto.getPartnerId()); // 合伙人ID
        createDTO.setCardNumber(dto.getPayoutAccount()); // 银行卡号：打款账户
        createDTO.setBusinessLicenseUrls(dto.getBusinessLicenseURLs()); // 营业执照URL
        createDTO.setNickname(dto.getMerchantName()); // 昵称：使用商家名称
        createDTO.setStatus(0); // 状态：0-禁用（商家还未通过审核）
        createDTO.setContactName(dto.getContactName()); // 联系人姓名
        createDTO.setLogo(dto.getLogo()); // 商户logo
        createDTO.setIdCard(dto.getIdCard()); // 身份证号
        createDTO.setMinimumOrderAmount(dto.getMinimumOrderAmount()); // 最低起送金额

        // 先创建商家用户
        Result<UserMchListVO> createResult = remoteMchUserService.createMchUser(createDTO);

        // 获取创建的商家用户ID
        Long mchUserId = createResult.getData().getId();

        // 调用审核表创建方法创建对应审核数据
        CreateAuditRecordDTO createAuditRecordDTO = CreateAuditRecordDTO
                .builder()
                .bizType(BizType.MERCHANT_SETTLE.getCode())
                .applicantId(mchUserId)
                .build();
        remoteMchUserService.createAuditRecord(createAuditRecordDTO);

        return Result.ok();
    }
}
