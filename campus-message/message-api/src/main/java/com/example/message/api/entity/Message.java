package com.example.message.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "站内消息")
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "消息ID")
	private Long id;

	@NotNull(message = "发送者用户ID不能为空")
	@Schema(description = "发送者用户ID，0表示系统通知")
	private Long senderId;

	@NotNull(message = "发送者类型不能为空")
	@Schema(description = "发送者类型：1系统/2管理员/3用户/4骑手/5商家/6合伙人")
	private Integer senderType;

	@Schema(description = "接收者用户ID")
	private Long receiverId;

	@NotNull(message = "接收者类型不能为空")
	@Schema(description = "接收者类型：1用户/2骑手/3商家/4合伙人")
	private Integer receiverType;

	@NotBlank(message = "消息标题不能为空")
	@Schema(description = "消息标题")
	private String messageTitle;

	@NotBlank(message = "消息内容不能为空")
	@Schema(description = "消息内容")
	private String messageContent;

	@Schema(description = "消息类型：system系统通知/remind提醒/private私信")
	private String messageType;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "发送时间")
	private LocalDateTime createTime;
}
