package com.example.finance.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询平台账户请求DTO
 */
@Data
@Schema(name = "PaymentAccountQueryDTO", description = "查询平台账户请求DTO")
public class PaymentAccountQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "账户名称", example = "平台主账户")
    private String accountName;

    @Schema(description = "状态：0-禁用 1-启用", example = "1")
    private Integer status;

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页数量", example = "10")
    private Integer pageSize = 10;
}