package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.admin.api.entity.SysOauthClientDetails;
import com.example.admin.biz.mapper.SysOauthClientDetailsMapper;
import com.example.admin.biz.service.SysOauthClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysOauthClientDetailsServiceImpl implements SysOauthClientDetailsService {

	private final SysOauthClientDetailsMapper sysOauthClientDetailsMapper;

	@Override
	public SysOauthClientDetails getClientDetailsById(String clientId) {
		LambdaQueryWrapper<SysOauthClientDetails> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(SysOauthClientDetails::getClientId, clientId);
		return sysOauthClientDetailsMapper.selectOne(wrapper);
	}
}
