package com.example.admin.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "学校列表查询DTO")
public class SchoolQueryDTO {

    @Schema(description = "当前页码，从1开始")
    private Integer page = 1;

    @Schema(description = "每页条数")
    private Integer size = 10;

    @Schema(description = "学校名称")
    private String orgName;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "区")
    private String district;

    @Schema(description = "合伙人ID")
    private Long partnerId;

    @Schema(description = "合伙人名称")
    private String partnerName;

    @Schema(description = "状态:1启用 0禁用")
    private Integer status;
}