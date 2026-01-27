package com.example.merchant.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(name = "MerchantQualificationApplyDTO", description = "商家资质申请（店铺认证）请求DTO")
public class MerchantQualificationApplyDTO {

    @Schema(description = "商家名称", example = "XX商贸有限公司", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "商家名称不能为空")
    private String merchantName;

    @Schema(description = "联系方式（手机号/微信等）", example = "13800000000", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "联系方式不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String contact;

    @Schema(description = "联系人姓名", example = "麻花腾")
    private String contactName;

    @Schema(description = "身份证号", example = "220193200409161421")
    private String idCard;

    @Schema(description = "商户logo", example = "www.example.com/upload/logo/mch1/123.jpg")
    private String logo;

    @Schema(description = "最低起送金额", example = "9.00")
    @Positive(message = "最低起送金额必须大于0")
    private BigDecimal minimumOrderAmount;

    @Schema(description = "所属合伙人ID（选择项）", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "所属合伙人ID不能为空")
    private Long partnerId;

    @Schema(description = "打款账户（银行卡号/支付宝/其他收款账号）", example = "6222************1234",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "打款账户不能为空")
    private String payoutAccount;

    @Schema(description = "邮箱", format = "email", example = "ops@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "营业执照类型：1=企业执照，2=个体户执照",
            example = "ENTERPRISE", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "营业执照类型不能为空")
    private Integer businessLicenseType;

    @Schema(description = "营业执照URL，若有多个，以逗号分割（上传后返回）", example = "upload/12345/mch1.jpg,upload/12345/mch2.jpg",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "营业执照URL不能为空")
    private String businessLicenseURLs;

    @Schema(description = "经营范围id集合（分类ID）", example = "[1,2,3]",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "经营范围不能为空")
    private List<Long> businessScopeCode;

}
