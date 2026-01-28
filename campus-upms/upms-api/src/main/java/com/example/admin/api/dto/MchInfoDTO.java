package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 商家信息DTO
 *
 * @author campus-upms
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MchInfoDTO", description = "商家信息DTO")
public class MchInfoDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Schema(description = "商家ID", example = "1")
	private Long mchId;

	@Schema(description = "商家名称", example = "奶茶店")
	private String mchName;

	@Schema(description = "商家logo", example = "http://example.com/logo.jpg")
	private String logo;

	@Schema(description = "是否营业：0-休息，1-营业", example = "1")
	private Integer isOpen;
}