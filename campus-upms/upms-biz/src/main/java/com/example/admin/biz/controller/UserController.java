package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.*;
import com.example.admin.api.vo.*;
import com.example.admin.biz.service.UserService;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import com.example.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "用户管理", description = "用户信息查询功能")
public class UserController {

	private final UserService userService;

	@GetMapping("/info/query")
	@Inner
	@Operation(summary = "查询用户信息", description = "通过用户名查询用户详细信息，包括角色信息")
	public Result<UserInfo> getUserInfo(String username) {
		UserInfo userInfo = userService.getUserInfo(username);
		if (userInfo == null) {
			return Result.failed("用户不存在");
		}
		return Result.ok(userInfo);
	}

	@GetMapping("/{id}/info")
	@Operation(summary = "通过用户ID查询用户信息", description = "通过用户ID查询用户详细信息，包括角色信息")
	public Result<UserInfo> getUserInfoById(@PathVariable Long id) {
		UserInfo userInfo = userService.getUserInfoById(id);
		if (userInfo == null) {
			return Result.failed("用户不存在");
		}
		return Result.ok(userInfo);
	}

	@GetMapping("/app-user/list")
	@Operation(summary = "分页查询C端用户列表", description = "根据查询条件分页查询C端用户列表，支持按用户名、手机号、状态、学校、注册时间等条件筛选")
	public Result<Page<UserAppListVO>> listAppUsers(@Valid @ParameterObject UserQueryDTO queryDTO) {
		queryDTO.setUserType(2);
		Page<UserAppListVO> page = userService.listAppUsers(queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/mch-user/list")
	@Operation(summary = "分页查询商家用户列表", description = "根据查询条件分页查询商家用户列表，支持按用户名、手机号、状态、注册时间等条件筛选")
	public Result<Page<UserMchListVO>> listMchUsers(@Valid @ParameterObject UserQueryDTO queryDTO) {
		queryDTO.setUserType(3);
		Page<UserMchListVO> page = userService.listMchUsers(queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/rider-user/list")
	@Operation(summary = "分页查询骑手用户列表", description = "根据查询条件分页查询骑手用户列表，支持按用户名、手机号、状态、注册时间等条件筛选")
	public Result<Page<UserRiderListVO>> listRiderUsers(@Valid @ParameterObject UserQueryDTO queryDTO) {
		queryDTO.setUserType(4);
		Page<UserRiderListVO> page = userService.listRiderUsers(queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/sys-user/list")
	@Operation(summary = "分页查询系统用户列表", description = "根据查询条件分页查询系统用户列表，支持按用户名、手机号、状态、注册时间等条件筛选")
	public Result<Page<UserSysListVO>> listSysUsers(@Valid @ParameterObject UserQueryDTO queryDTO) {
		queryDTO.setUserType(1);
		Page<UserSysListVO> page = userService.listSysUsers(queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/partner-user/list")
	@Operation(summary = "分页查询合伙人用户列表", description = "根据查询条件分页查询合伙人用户列表，支持按用户名、手机号、状态、注册时间等条件筛选")
	public Result<Page<UserPartnerListVO>> listPartnerUsers(@Valid @ParameterObject UserQueryDTO queryDTO) {
		queryDTO.setUserType(5);
		Page<UserPartnerListVO> page = userService.listPartnerUsers(queryDTO);
		return Result.ok(page);
	}

	@PutMapping("/app-user/{id}/status")
	@Operation(summary = "修改用户状态（拉黑/解封）", description = "修改指定用户的状态，支持拉黑或解封操作。需要提供目标状态和操作原因")
	public Result<Void> updateUserStatus(@PathVariable Long id, @Valid @RequestBody com.example.admin.api.dto.UserStatusDTO statusDTO) {
		userService.updateUserStatus(id, statusDTO);
		return Result.ok();
	}


	@PutMapping("/{id}")
	@Operation(summary = "编辑用户", description = "编辑用户信息，支持联表更新base_user和对应的扩展表，updateDTO格式以userType为准寻找对应的DTO：" +
			"1.UpdateUserAppDTO C端用户更新DTO" +
			"2.UpdateUserMchDTO 商家用户更新DTO" +
			"3.UpdateUserRiderDTO 骑手用户更新DTO" +
			"4.UpdateUserSysDTO 系统用户更新DTO" +
			"5.UpdateUserPartnerDTO 合伙人用户更新DTO")
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

	@PostMapping("/app-user/create")
	@Operation(summary = "创建C端用户", description = "创建新的C端用户，同时插入base_user表和user_app表。需要提供用户名、密码、手机号等必填信息")
	public Result<UserAppListVO> createAppUser(@Valid @RequestBody CreateAppUserDTO dto) {
		UserAppListVO result = userService.createAppUser(dto);
		return Result.ok(result);
	}

	@PostMapping("/mch-user/create")
	@Operation(summary = "创建商家用户", description = "创建新的商家用户，同时插入base_user表和user_mch表。需要提供用户名、密码、手机号、商户名等必填信息")
	public Result<UserMchListVO> createMchUser(@Valid @RequestBody CreateMchUserDTO dto) {
		UserMchListVO result = userService.createMchUser(dto);
		return Result.ok(result);
	}

	@PostMapping("/rider-user/create")
	@Operation(summary = "创建骑手用户", description = "创建新的骑手用户，同时插入base_user表和user_rider表。需要提供用户名、密码、手机号、真实姓名、身份证号等必填信息")
	public Result<UserRiderListVO> createRiderUser(@Valid @RequestBody CreateRiderUserDTO dto) {
		UserRiderListVO result = userService.createRiderUser(dto);
		return Result.ok(result);
	}

	@PostMapping("/sys-user/create")
	@Operation(summary = "创建系统用户", description = "创建新的系统用户，同时插入base_user表和user_sys表。需要提供用户名、密码、手机号等必填信息")
	public Result<UserSysListVO> createSysUser(@Valid @RequestBody CreateSysUserDTO dto) {
		UserSysListVO result = userService.createSysUser(dto);
		return Result.ok(result);
	}

	@PostMapping("/partner-user/create")
	@Operation(summary = "创建合伙人用户", description = "创建新的合伙人用户，同时插入base_user表和user_partner表。需要提供用户名、密码、手机号、合伙人姓名等必填信息")
	public Result<UserPartnerListVO> createPartnerUser(@Valid @RequestBody CreatePartnerUserDTO dto) {
		UserPartnerListVO result = userService.createPartnerUser(dto);
		return Result.ok(result);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除用户", description = "删除指定用户（逻辑删除），同时删除用户角色关联")
	public Result<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return Result.ok();
	}

	@GetMapping("/sys-user/{id}/detail")
	@Operation(summary = "查询系统用户详情", description = "根据用户ID查询系统用户的详细信息，包括基础信息、扩展信息和角色信息")
	public Result<UserSysListVO> getSysUserDetail(@PathVariable Long id) {
		UserSysListVO detail = userService.getSysUserDetail(id);
		return Result.ok(detail);
	}

	@GetMapping("/app-user/{id}/detail")
	@Operation(summary = "查询C端用户详情", description = "根据用户ID查询C端用户的详细信息，包括基础信息、学号、学校、余额和角色信息")
	public Result<UserAppListVO> getAppUserDetail(@PathVariable Long id) {
		UserAppListVO detail = userService.getAppUserDetail(id);
		return Result.ok(detail);
	}

	@GetMapping("/mch-user/{id}/detail")
	@Operation(summary = "查询商家用户详情", description = "根据用户ID查询商家用户的详细信息，包括基础信息、商户信息、营业状态和角色信息")
	public Result<UserMchListVO> getMchUserDetail(@PathVariable Long id) {
		UserMchListVO detail = userService.getMchUserDetail(id);
		return Result.ok(detail);
	}

	@GetMapping("/rider-user/{id}/detail")
	@Operation(summary = "查询骑手用户详情", description = "根据用户ID查询骑手用户的详细信息，包括基础信息、身份证、余额、收益和角色信息")
	public Result<UserRiderListVO> getRiderUserDetail(@PathVariable Long id) {
		UserRiderListVO detail = userService.getRiderUserDetail(id);
		return Result.ok(detail);
	}

	@GetMapping("/partner-user/{id}/detail")
	@Operation(summary = "查询合伙人用户详情", description = "根据用户ID查询合伙人用户的详细信息，包括基础信息、邀请码、分佣比例、上级合伙人和角色信息")
	public Result<UserPartnerListVO> getPartnerUserDetail(@PathVariable Long id) {
		UserPartnerListVO detail = userService.getPartnerUserDetail(id);
		return Result.ok(detail);
	}

	@GetMapping("/api/mch/{baseUserId}")
	@Inner
	@Operation(summary = "根据商家用户ID获取商家信息", description = "根据商家用户ID获取商家信息，包括商家ID、商家名称、logo、营业状态等")
	public Result<MchInfoDTO> getMchInfoByBaseUserId(@PathVariable Long baseUserId) {
		MchInfoDTO mchInfo = userService.getMchInfoByBaseUserId(baseUserId);
		if (mchInfo == null) {
			return Result.failed("商家信息不存在");
		}
		return Result.ok(mchInfo);
	}

	@PostMapping("/api/mch/batch")
	@Inner
	@Operation(summary = "批量获取商家信息", description = "根据商家用户ID列表批量获取商家信息")
	public Result<List<MchInfoDTO>> batchGetMchInfo(@RequestBody List<Long> baseUserIds) {
		List<MchInfoDTO> mchInfoList = userService.batchGetMchInfo(baseUserIds);
		return Result.ok(mchInfoList);
	}
}
