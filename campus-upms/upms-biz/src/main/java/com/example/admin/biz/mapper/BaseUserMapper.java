package com.example.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admin.api.entity.BaseUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseUserMapper extends BaseMapper<BaseUser> {
}
