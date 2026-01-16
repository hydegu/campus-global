package com.example.admin.biz.controller;

import com.example.admin.api.dto.UserInfo;
import com.example.admin.biz.service.UserService;
import com.example.common.core.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户信息查询功能")
public class UserController {

	private final UserService userService;

	@GetMapping("/info/query")
	@Operation(summary = "查询用户信息", description = "通过用户名查询用户详细信息，包括角色信息")
	public Result<UserInfo> getUserInfo(String username) {
		UserInfo userInfo = userService.getUserInfo(username);
		if (userInfo == null) {
			return Result.failed("用户不存在");
		}
		return Result.ok(userInfo);
	}
}
