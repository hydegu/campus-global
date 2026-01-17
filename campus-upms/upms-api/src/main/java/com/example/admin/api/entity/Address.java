package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "地址表")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "地址ID")
	private Long id;

	@Schema(description = "省")
	private String province;

	@Schema(description = "市")
	private String city;

	@Schema(description = "区")
	private String district;

	@TableField("detail_address")
	@Schema(description = "详细地址")
	private String detailAddress;

	@TableField("contact_phone")
	@Schema(description = "联系人电话")
	private String contactPhone;

	@TableField("contact_name")
	@Schema(description = "联系人姓名")
	private String contactName;

	@TableField("receiver_lat")
	@Schema(description = "收货地址纬度")
	private BigDecimal receiverLat;

	@TableField("receiver_lng")
	@Schema(description = "收货地址经度")
	private BigDecimal receiverLng;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "更新时间")
	private LocalDateTime updateTime;

	@TableLogic
	@TableField("delete_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "软删除时间")
	private LocalDateTime deleteTime;
}
