package com.example.merchant.api.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CommissionInfo", description = "分佣配置信息")
public class CommissionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "配置ID", example = "1")
    private Long id;

    @Schema(description = "分类ID", example = "1")
    private Long categoryId;

    @Schema(description = "配置类型：1-全局默认，2-服务分佣，3-商家分佣，4-合伙人分佣", example = "2")
    private Integer configType;

    @Schema(description = "分佣比例，10代表10%", example = "15")
    private Integer commissionRate;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    private Integer status;
}