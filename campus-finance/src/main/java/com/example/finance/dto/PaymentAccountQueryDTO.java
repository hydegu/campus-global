package com.example.finance.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "账户查询DTO")
public class PaymentAccountQueryDTO {
    /**
     * 页码
     */
    @Schema(description = "页码", defaultValue = "1")
    private Integer page;

    /**
     * 每页大小
     */
    @Schema(description = "每页大小", defaultValue = "10")
    private Integer size;

    /**
     * 账户名称
     */
    @Schema(description = "账户名称")
    private String accountName;

    /**
     * 账户编码
     */
    @Schema(description = "账户编码")
    private String accountCode;

    /**
     * 银行名称
     */
    @Schema(description = "银行名称")
    private String bankName;

    /**
     * 状态
     */
    @Schema(description = "状态(0-禁用 1-启用)")
    private Integer status;
}
