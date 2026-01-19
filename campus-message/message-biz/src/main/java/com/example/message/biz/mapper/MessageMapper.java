package com.example.message.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.message.api.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
