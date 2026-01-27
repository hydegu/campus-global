package com.example.merchant.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "MerchantQualificationApplyDTO", description = "商家资质申请（店铺认证）请求DTO")
public class MerchantQualificationApplyDTO {

    @Schema(description = "商家名称", example = "XX商贸有限公司", requiredMode = Schema.RequiredMode.REQUIRED)
    private String merchantName;

    @Schema(description = "联系方式（手机号/微信等）", example = "13800000000", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contact;

    @Schema(description = "所属合伙人ID（选择项）", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long partnerId;

    @Schema(description = "打款账户（银行卡号/支付宝/其他收款账号）", example = "6222************1234",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String payoutAccount;

    @Schema(description = "邮箱", format = "email", example = "ops@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "营业执照类型：1=企业执照，2=个体户执照",
            example = "ENTERPRISE", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer businessLicenseType;

    @Schema(description = "营业执照URL，若有多个，以逗号分割（上传后返回）", example = "upload/12345/mch1.jpg,upload/12345/mch2.jpg",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String businessLicenseURLs;

    @Schema(description = "经营范围id集合（分类ID）", example = "[1,2,3]",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> businessScopeCode;

}
