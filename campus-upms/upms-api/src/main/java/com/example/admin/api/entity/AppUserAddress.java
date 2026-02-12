package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("app_user_address")
@Schema(description = "APP用户地址关联表")
public class AppUserAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户ID")
	private Long userId;

	@Schema(description = "地址ID")
	private Long addressId;
}