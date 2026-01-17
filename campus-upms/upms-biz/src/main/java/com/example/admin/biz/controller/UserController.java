package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.biz.dto.*;
import com.example.admin.biz.service.UserService;
import com.example.admin.biz.vo.*;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
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

	@GetMapping("/app-user/list")
	@Operation(summary = "分页查询C端用户列表", description = "根据查询条件分页查询C端用户列表，支持按用户名、手机号、状态、学校、注册时间等条件筛选")
	public Result<Page<UserAppListVO>> listAppUsers(@Valid UserQueryDTO queryDTO) {
		queryDTO.setUserType(2);
		Page<UserAppListVO> page = userService.listAppUsers(queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/mch-user/list")
	@Operation(summary = "分页查询商家用户列表", description = "根据查询条件分页查询商家用户列表，支持按用户名、手机号、状态、注册时间等条件筛选")
	public Result<Page<UserMchListVO>> listMchUsers(@Valid UserQueryDTO queryDTO) {
		queryDTO.setUserType(3);
		Page<UserMchListVO> page = userService.listMchUsers(queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/rider-user/list")
	@Operation(summary = "分页查询骑手用户列表", description = "根据查询条件分页查询骑手用户列表，支持按用户名、手机号、状态、注册时间等条件筛选")
	public Result<Page<UserRiderListVO>> listRiderUsers(@Valid UserQueryDTO queryDTO) {
		queryDTO.setUserType(4);
		Page<UserRiderListVO> page = userService.listRiderUsers(queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/sys-user/list")
	@Operation(summary = "分页查询系统用户列表", description = "根据查询条件分页查询系统用户列表，支持按用户名、手机号、状态、注册时间等条件筛选")
	public Result<Page<UserSysListVO>> listSysUsers(@Valid UserQueryDTO queryDTO) {
		queryDTO.setUserType(1);
		Page<UserSysListVO> page = userService.listSysUsers(queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/partner-user/list")
	@Operation(summary = "分页查询合伙人用户列表", description = "根据查询条件分页查询合伙人用户列表，支持按用户名、手机号、状态、注册时间等条件筛选")
	public Result<Page<UserPartnerListVO>> listPartnerUsers(@Valid UserQueryDTO queryDTO) {
		queryDTO.setUserType(5);
		Page<UserPartnerListVO> page = userService.listPartnerUsers(queryDTO);
		return Result.ok(page);
	}

	@PutMapping("/app-user/{id}/status")
	@Operation(summary = "修改用户状态（拉黑/解封）", description = "修改指定用户的状态，支持拉黑或解封操作。需要提供目标状态和操作原因")
	public Result<Void> updateUserStatus(@PathVariable Long id, @Valid @RequestBody UserStatusDTO statusDTO) {
		userService.updateUserStatus(id, statusDTO);
		return Result.ok();
	}


	@PutMapping("/{id}")
	@Operation(summary = "编辑用户", description = "编辑用户信息，支持联表更新base_user和对应的扩展表")
	public Result<Void> updateUser(@PathVariable Long id, @RequestParam Integer userType, @Valid @RequestBody Object updateDTO) {
		userService.updateUser(id, userType, updateDTO);
		return Result.ok();
	}

	@PutMapping("/{id}/reset-password")
	@Operation(summary = "重置用户密码", description = "重置指定用户的密码")
	public Result<Void> resetUserPassword(@PathVariable Long id, @Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
		userService.resetUserPassword(id, resetPasswordDTO);
		return Result.ok();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除用户", description = "删除指定用户（逻辑删除），同时删除用户角色关联")
	public Result<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return Result.ok();
	}
}
