package com.example.message.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.core.exception.BusinessException;
import com.example.message.api.dto.MessageQueryDTO;
import com.example.message.api.dto.MessageSendDTO;
import com.example.message.api.entity.Message;
import com.example.message.api.enums.MessageTypeEnum;
import com.example.message.api.enums.SenderTypeEnum;
import com.example.message.api.feign.RemoteUserService;
import com.example.message.api.vo.MessageDetailVO;
import com.example.message.api.vo.MessageVO;
import com.example.message.biz.mapper.MessageMapper;
import com.example.message.biz.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

	private final MessageMapper messageMapper;
	private final RemoteUserService remoteUserService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void sendMessage(MessageSendDTO sendDTO) {
		if (sendDTO == null) {
			throw new BusinessException("INVALID_PARAM", "发送消息参数不能为空");
		}

		if (sendDTO.getReceiverId() == null) {
			throw new BusinessException("INVALID_PARAM", "接收者用户ID不能为空");
		}

		if (sendDTO.getReceiverType() == null) {
			throw new BusinessException("INVALID_PARAM", "接收者类型不能为空");
		}

		Message message = new Message();
		BeanUtils.copyProperties(sendDTO, message);

		if (message.getSenderId() == null) {
			message.setSenderId(0L);
		}

		String validatedMessageType = validateMessageType(message.getSenderType(), message.getMessageType());
		message.setMessageType(validatedMessageType);

		messageMapper.insert(message);

		log.info("消息发送成功，接收者ID：{}，接收者类型：{}，消息标题：{}", 
				message.getReceiverId(), message.getReceiverType(), message.getMessageTitle());
	}

	private String validateMessageType(Integer senderType, String messageType) {
		if (!StringUtils.hasText(messageType)) {
			throw new BusinessException("INVALID_MESSAGE_TYPE", "消息类型不能为空");
		}

		SenderTypeEnum senderTypeEnum = SenderTypeEnum.getByCode(senderType);
		if (senderTypeEnum == null) {
			throw new BusinessException("INVALID_SENDER_TYPE", "发送者类型不合法");
		}

		MessageTypeEnum messageTypeEnum = MessageTypeEnum.getByCode(messageType);
		if (messageTypeEnum == null) {
			throw new BusinessException("INVALID_MESSAGE_TYPE", "消息类型不合法");
		}

		switch (senderTypeEnum) {
			case SYSTEM:
				if (!MessageTypeEnum.SYSTEM.equals(messageTypeEnum)) {
					throw new BusinessException("INVALID_MESSAGE_TYPE", "系统发送者只能发送系统通知");
				}
				break;
			case USER:
				if (!MessageTypeEnum.PRIVATE.equals(messageTypeEnum)) {
					throw new BusinessException("INVALID_MESSAGE_TYPE", "用户只能发送私信");
				}
				break;
			case ADMIN:
			case RIDER:
			case MERCHANT:
			case PARTNER:
				if (!MessageTypeEnum.REMIND.equals(messageTypeEnum) && !MessageTypeEnum.PRIVATE.equals(messageTypeEnum)) {
					throw new BusinessException("INVALID_MESSAGE_TYPE", "该发送者类型只能发送提醒或私信");
				}
				break;
			default:
				throw new BusinessException("INVALID_SENDER_TYPE", "不支持的发送者类型");
		}
		
		return messageType;
	}

	@Override
	public Page<MessageVO> listMessages(Long currentUserId, MessageQueryDTO queryDTO) {
		if (currentUserId == null || currentUserId == 0) {
			throw new BusinessException("NOT_LOGIN", "用户未登录");
		}

		if (queryDTO == null) {
			queryDTO = new MessageQueryDTO();
		}

		if (queryDTO.getCurrent() == null || queryDTO.getCurrent() < 1) {
			queryDTO.setCurrent(1);
		}

		if (queryDTO.getSize() == null || queryDTO.getSize() < 1) {
			queryDTO.setSize(10);
		}

		LambdaQueryWrapper<Message> wrapper = buildQueryWrapper(currentUserId, queryDTO);

		IPage<Message> messagePage = messageMapper.selectPage(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper);

		Page<MessageVO> resultPage = new Page<>(queryDTO.getCurrent(), queryDTO.getSize());
		resultPage.setTotal(messagePage.getTotal());

		if (messagePage.getRecords().isEmpty()) {
			return resultPage;
		}

		resultPage.setRecords(messagePage.getRecords().stream().map(message -> {
			MessageVO vo = new MessageVO();
			BeanUtils.copyProperties(message, vo);
			vo.setSenderName(getSenderName(message.getSenderId(), message.getSenderType()));
			return vo;
		}).collect(java.util.stream.Collectors.toList()));

		return resultPage;
	}

	@Override
	public MessageDetailVO getMessageDetail(Long currentUserId, Long id) {
		if (currentUserId == null || currentUserId == 0) {
			throw new BusinessException("NOT_LOGIN", "用户未登录");
		}

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "消息ID不能为空");
		}

		Message message = messageMapper.selectById(id);
		if (message == null) {
			throw new BusinessException("MESSAGE_NOT_FOUND", "消息不存在");
		}

		if (!message.getReceiverId().equals(currentUserId) && !isSystemMessage(message)) {
			throw new BusinessException("PERMISSION_DENIED", "无权查看该消息");
		}

		MessageDetailVO vo = new MessageDetailVO();
		BeanUtils.copyProperties(message, vo);
		vo.setSenderName(getSenderName(message.getSenderId(), message.getSenderType()));
		vo.setReceiverName(getReceiverName(message.getReceiverId(), message.getReceiverType()));

		return vo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteMessage(Long currentUserId, Long id) {
		if (currentUserId == null || currentUserId == 0) {
			throw new BusinessException("NOT_LOGIN", "用户未登录");
		}

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "消息ID不能为空");
		}

		Message message = messageMapper.selectById(id);
		if (message == null) {
			throw new BusinessException("MESSAGE_NOT_FOUND", "消息不存在");
		}

		if (!message.getReceiverId().equals(currentUserId)) {
			throw new BusinessException("PERMISSION_DENIED", "无权删除该消息");
		}

		messageMapper.deleteById(id);

		log.info("消息删除成功，消息ID：{}", id);
	}

	private LambdaQueryWrapper<Message> buildQueryWrapper(Long currentUserId, MessageQueryDTO queryDTO) {
		LambdaQueryWrapper<Message> wrapper = Wrappers.lambdaQuery();

		wrapper.eq(Message::getReceiverId, currentUserId);

		if (queryDTO.getSenderType() != null) {
			wrapper.eq(Message::getSenderType, queryDTO.getSenderType());
		}

		if (StringUtils.hasText(queryDTO.getMessageType())) {
			wrapper.eq(Message::getMessageType, queryDTO.getMessageType());
		}

		if (queryDTO.getStartTime() != null) {
			wrapper.ge(Message::getCreateTime, queryDTO.getStartTime());
		}

		if (queryDTO.getEndTime() != null) {
			wrapper.le(Message::getCreateTime, queryDTO.getEndTime());
		}

		wrapper.orderByDesc(Message::getCreateTime);

		return wrapper;
	}

	private String getSenderName(Long senderId, Integer senderType) {
		if (senderId == null || senderId == 0) {
			return "系统";
		}
		try {
			com.example.common.core.util.Result<com.example.admin.api.dto.UserInfo> result = remoteUserService.getUserInfoById(senderId);
			if (result != null && result.getData() != null) {
				return result.getData().getNickname();
			}
		} catch (Exception e) {
			log.warn("获取发送者名称失败，senderId：{}", senderId, e);
		}
		return "用户" + senderId;
	}

	private String getReceiverName(Long receiverId, Integer receiverType) {
		if (receiverId == null) {
			return "未知用户";
		}
		try {
			com.example.common.core.util.Result<com.example.admin.api.dto.UserInfo> result = remoteUserService.getUserInfoById(receiverId);
			if (result != null && result.getData() != null) {
				return result.getData().getNickname();
			}
		} catch (Exception e) {
			log.warn("获取接收者名称失败，receiverId：{}", receiverId, e);
		}
		return "用户" + receiverId;
	}

	private boolean isSystemMessage(Message message) {
		return message.getSenderType() != null && message.getSenderType() == 1 
			|| "system".equals(message.getMessageType());
	}
}
