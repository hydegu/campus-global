package com.example.admin.biz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "学校信息响应VO")
public class SysSchoolVO {

    @Schema(description = "学校ID")
    private Long id;

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

    @Schema(description = "合伙人名称")
    private String partnerName;

    @Schema(description = "状态:1启用 0禁用")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}