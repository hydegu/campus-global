package com.example.message.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "消息列表VO")
public class MessageVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "消息ID")
	private Long id;

	@Schema(description = "发送者用户ID")
	private Long senderId;

	@Schema(description = "发送者类型：1系统/2管理员/3用户/4骑手/5商家/6合伙人")
	private Integer senderType;

	@Schema(description = "发送者名称")
	private String senderName;

	@Schema(description = "接收者用户ID")
	private Long receiverId;

	@Schema(description = "接收者类型：1用户/2骑手/3商家/4合伙人")
	private Integer receiverType;

	@Schema(description = "消息标题")
	private String messageTitle;

	@Schema(description = "消息内容")
	private String messageContent;

	@Schema(description = "消息类型：system系统通知/remind提醒/private私信")
	private String messageType;

	@Schema(description = "发送时间")
	private LocalDateTime createTime;
}
