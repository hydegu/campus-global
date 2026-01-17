package com.example.admin.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Schema(description = "新增学校请求DTO")
public class SchoolAddDTO {

    @NotBlank(message = "学校名称不能为空")
    @Schema(description = "学校名称")
    private String orgName;

    @NotBlank(message = "省份不能为空")
    @Schema(description = "省")
    private String province;

    @NotBlank(message = "城市不能为空")
    @Schema(description = "市")
    private String city;

    @NotBlank(message = "区县不能为空")
    @Schema(description = "区")
    private String district;

    @NotBlank(message = "详细地址不能为空")
    @Schema(description = "详细地址")
    private String address;

    @NotBlank(message = "联系人不能为空")
    @Schema(description = "联系人")
    private String contactPerson;

    @NotBlank(message = "联系电话不能为空")
    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "合伙人ID")
    private Long partnerId;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态:1启用 0禁用")
    private Integer status;
}