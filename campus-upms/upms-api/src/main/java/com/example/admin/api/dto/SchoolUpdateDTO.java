package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "学校信息更新DTO")
public class SchoolUpdateDTO {

    @Schema(description = "学校名称")
    private String orgName;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "区")
    private String district;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "合伙人ID")
    private Long partnerId;

    @Schema(description = "状态:1启用 0禁用")
    private Integer status;
}