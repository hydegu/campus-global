package com.example.merchant.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "营业更改DTO")
public class BusinessUpdateDTO {

    @Schema(description = "是否营业 1-营业 0-未营业",example = "1")
    private Integer isOpen;

    @Schema(description = "营业开始时间",example = "08:00")
    private LocalTime businessStartTime;

    @Schema(description = "营业结束时间",example = "20:00")
    private LocalTime businessEndTime;
}
