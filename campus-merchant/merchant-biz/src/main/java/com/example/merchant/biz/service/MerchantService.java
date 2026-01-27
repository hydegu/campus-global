package com.example.merchant.biz.service;

import com.example.common.core.util.Result;
import com.example.merchant.api.dto.MerchantQualificationApplyDTO;

/**
 * 商家
 */
public interface MerchantService {

    public Result<Void> applyForMchVerification(MerchantQualificationApplyDTO dto);
}
