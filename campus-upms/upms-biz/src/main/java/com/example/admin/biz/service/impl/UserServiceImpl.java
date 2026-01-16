package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.api.entity.BaseUser;
import com.example.admin.api.entity.Role;
import com.example.admin.api.entity.UserRole;
import com.example.admin.biz.mapper.BaseUserMapper;
import com.example.admin.biz.mapper.RoleMapper;
import com.example.admin.biz.mapper.UserRoleMapper;
import com.example.admin.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final BaseUserMapper baseUserMapper;
	private final UserRoleMapper userRoleMapper;
	private final RoleMapper roleMapper;

	@Override
	public UserInfo getUserInfo(String username) {
		LambdaQueryWrapper<BaseUser> userWrapper = Wrappers.lambdaQuery();
		userWrapper.eq(BaseUser::getUsername, username);
		BaseUser baseUser = baseUserMapper.selectOne(userWrapper);

		if (baseUser == null) {
			return null;
		}

		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(baseUser.getId());
		userInfo.setUsername(baseUser.getUsername());
		userInfo.setNickname(baseUser.getNickname());
		userInfo.setPassword(baseUser.getPassword());
		userInfo.setPhone(baseUser.getPhone());
		userInfo.setEmail(baseUser.getEmail());
		userInfo.setAvatar(baseUser.getAvatar());
		userInfo.setStatus(baseUser.getStatus());
		userInfo.setUserType(baseUser.getUserType());
		userInfo.setCreateAt(baseUser.getCreateAt());
		userInfo.setUpdateAt(baseUser.getUpdateAt());
		userInfo.setDeleteAt(baseUser.getDeleteAt());

		LambdaQueryWrapper<UserRole> userRoleWrapper = Wrappers.lambdaQuery();
		userRoleWrapper.eq(UserRole::getUserId, baseUser.getId());
		List<UserRole> userRoles = userRoleMapper.selectList(userRoleWrapper);

		if (userRoles != null && !userRoles.isEmpty()) {
			List<Long> roleIds = userRoles.stream()
					.map(UserRole::getRoleId)
					.collect(Collectors.toList());

			LambdaQueryWrapper<Role> roleWrapper = Wrappers.lambdaQuery();
			roleWrapper.in(Role::getId, roleIds);
			List<Role> roles = roleMapper.selectList(roleWrapper);
			userInfo.setRoleList(roles);
		} else {
			userInfo.setRoleList(Collections.emptyList());
		}

		return userInfo;
	}
}
