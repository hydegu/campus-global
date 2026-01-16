package com.example.admin.biz.service;

import com.example.admin.api.entity.SysOauthClientDetails;

public interface SysOauthClientDetailsService {

	SysOauthClientDetails getClientDetailsById(String clientId);
}
