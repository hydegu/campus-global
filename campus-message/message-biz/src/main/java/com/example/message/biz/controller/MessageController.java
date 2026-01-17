package com.example.message.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import com.example.common.security.util.SecurityUtils;
import com.example.message.api.dto.MessageQueryDTO;
import com.example.message.api.dto.MessageSendDTO;
import com.example.message.api.vo.MessageDetailVO;
import com.example.message.api.vo.MessageVO;
import com.example.message.biz.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "消息管理", description = "站内消息发送、查询、删除功能")
public class MessageController {

	private final MessageService messageService;

	@PostMapping("/send")
	@Operation(summary = "发送消息", description = "发送站内消息，支持系统通知、提醒、私信等类型")
	public Result<Void> sendMessage(@Valid @RequestBody MessageSendDTO sendDTO) {
		messageService.sendMessage(sendDTO);
		return Result.ok();
	}

	@GetMapping("/list")
	@Operation(summary = "分页查询消息列表", description = "根据查询条件分页查询当前用户的消息列表，支持按发送者类型、消息类型、时间范围等条件筛选")
	public Result<Page<MessageVO>> listMessages(@Valid @ParameterObject MessageQueryDTO queryDTO) {
		Long currentUserId = SecurityUtils.getCurrentUserId();
		Page<MessageVO> page = messageService.listMessages(currentUserId, queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/{id}")
	@Operation(summary = "查询消息详情", description = "根据消息ID查询当前用户的消息详细信息")
	public Result<MessageDetailVO> getMessageDetail(@PathVariable Long id) {
		Long currentUserId = SecurityUtils.getCurrentUserId();
		MessageDetailVO detailVO = messageService.getMessageDetail(currentUserId, id);
		return Result.ok(detailVO);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除消息", description = "根据消息ID删除当前用户的消息")
	public Result<Void> deleteMessage(@PathVariable Long id) {
		Long currentUserId = SecurityUtils.getCurrentUserId();
		messageService.deleteMessage(currentUserId, id);
		return Result.ok();
	}
}
