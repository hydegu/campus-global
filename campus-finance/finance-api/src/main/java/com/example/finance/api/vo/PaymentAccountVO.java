package com.example.finance.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 平台账户详情VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PaymentAccountVO", description = "平台账户详情VO")
public class PaymentAccountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "账户ID", example = "1")
    private Long id;

    @Schema(description = "账户名称", example = "平台主账户")
    private String accountName;

    @Schema(description = "账户编码", example = "ACC001")
    private String accountCode;

    @Schema(description = "联系电话", example = "13800138000")
    private String contactPhone;

    @Schema(description = "联系邮箱", example = "finance@example.com")
    private String contactEmail;

    @Schema(description = "账号", example = "6222021001234567890")
    private String bankAccountNumber;

    @Schema(description = "开户名", example = "某某公司")
    private String bankAccountName;

    @Schema(description = "开户银行", example = "中国工商银行")
    private String bankName;

    @Schema(description = "开户支行", example = "北京朝阳支行")
    private String bankBranch;

    @Schema(description = "最后支付时间", example = "2024-01-30 14:00:00")
    private LocalDateTime lastPaymentTime;

    @Schema(description = "状态：0-禁用 1-启用", example = "1")
    private Integer status;

    @Schema(description = "状态名称", example = "启用")
    private String statusName;

    @Schema(description = "创建时间", example = "2024-01-01 00:00:00")
    private LocalDateTime createAt;
}