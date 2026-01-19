package com.example.message.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "消息查询DTO")
public class MessageQueryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "发送者类型：1系统/2管理员/3用户/4骑手/5商家/6合伙人")
	private Integer senderType;

	@Schema(description = "消息类型：system系统通知/remind提醒/private私信")
	private String messageType;

	@Schema(description = "开始时间")
	private LocalDateTime startTime;

	@Schema(description = "结束时间")
	private LocalDateTime endTime;

	@Schema(description = "当前页码")
	private Integer current;

	@Schema(description = "每页条数")
	private Integer size;
}
