package com.example.message.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.message.api.dto.MessageQueryDTO;
import com.example.message.api.dto.MessageSendDTO;
import com.example.message.api.vo.MessageDetailVO;
import com.example.message.api.vo.MessageVO;

public interface MessageService {

	void sendMessage(MessageSendDTO sendDTO);

	Page<MessageVO> listMessages(Long currentUserId, MessageQueryDTO queryDTO);

	MessageDetailVO getMessageDetail(Long currentUserId, Long id);

	void deleteMessage(Long currentUserId, Long id);
}
