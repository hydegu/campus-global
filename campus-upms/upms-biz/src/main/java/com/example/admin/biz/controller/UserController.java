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
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "用户管理", description = "用户信息查询功能")
public class UserController {

	private final UserService userService;

	@GetMapping("/info/query")
	@Inner
	@Operation(summary = "根据用户名查询用户信息", description = "根据用户名查询用户的基础信息，供内部服务调用")
	public Result<UserInfo> getUserInfo(String username) {
		UserInfo userInfo = userService.getUserInfo(username);
		if (userInfo == null) {
			return Result.failed("用户不存在");
		}
		return Result.ok(userInfo);
	}

	@GetMapping("/{id}/info")
	@Inner
	@Operation(summary = "根据用户ID查询用户信息", description = "根据用户ID查询用户的基础信息，供内部服务调用")
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


	@PutMapping("/sys-user/{id}")
	@Operation(summary = "编辑系统用户", description = "编辑系统用户信息，支持更新用户名、手机号、头像、状态、真实姓名、性别、出生日期、部门、职位、邮箱等信息，支持编辑角色")
	public Result<Void> updateSysUser(@PathVariable Long id, @Valid @RequestBody UpdateUserSysDTO dto) {
		userService.updateSysUser(id, dto);
		return Result.ok();
	}

	@PutMapping("/app-user/{id}")
	@Operation(summary = "编辑C端用户", description = "编辑C端用户信息，支持更新用户名、手机号、头像、状态、学校ID、真实姓名、性别、生日、地址、学号等信息，支持编辑角色")
	public Result<Void> updateAppUser(@PathVariable Long id, @Valid @RequestBody UpdateUserAppDTO dto) {
		userService.updateAppUser(id, dto);
		return Result.ok();
	}

	@PutMapping("/mch-user/{id}")
	@Operation(summary = "编辑商家用户", description = "编辑商家用户信息，支持更新用户名、手机号、头像、状态、商家名称、营业执照、地址信息、打款账户、经营时间、联系人、经营范围、分佣比例、最低起送金额等信息，支持编辑角色")
	public Result<Void> updateMchUser(@PathVariable Long id, @Valid @RequestBody UpdateUserMchDTO dto) {
		userService.updateMchUser(id, dto);
		return Result.ok();
	}

	@PutMapping("/rider-user/{id}")
	@Operation(summary = "编辑骑手用户", description = "编辑骑手用户信息，支持更新用户名、手机号、头像、状态、真实姓名、身份证号、性别、银行卡号、地址信息、紧急联系人、身份证照片、分佣比例等信息，支持编辑角色")
	public Result<Void> updateRiderUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRiderDTO dto) {
		userService.updateRiderUser(id, dto);
		return Result.ok();
	}

	@PutMapping("/partner-user/{id}")
	@Operation(summary = "编辑合伙人用户", description = "编辑合伙人用户信息，支持更新用户名、手机号、头像、状态、合伙人姓名、银行卡号、分佣比例、上级合伙人ID等信息，支持编辑角色")
	public Result<Void> updatePartnerUser(@PathVariable Long id, @Valid @RequestBody UpdateUserPartnerDTO dto) {
		userService.updatePartnerUser(id, dto);
		return Result.ok();
	}

	@PutMapping("/{id}/reset-password")
	@Operation(summary = "重置用户密码", description = "重置指定用户的密码")
	public Result<Void> resetUserPassword(@PathVariable Long id, @Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
		userService.resetUserPassword(id, resetPasswordDTO);
		return Result.ok();
	}

	@PostMapping("/app-user/create")
	@Operation(summary = "创建C端用户", description = "创建新的C端用户，同时插入base_user表和user_app表。需要提供用户名、密码、手机号等必填信息，支持分配角色")
	public Result<UserAppListVO> createAppUser(@Valid @RequestBody CreateAppUserDTO dto) {
		UserAppListVO result = userService.createAppUser(dto);
		return Result.ok(result);
	}

	@PostMapping("/mch-user/create")
	@Operation(summary = "创建商家用户", description = "创建新的商家用户，同时插入base_user表和user_mch表。需要提供用户名、密码、手机号、商户名等必填信息，支持分配角色")
	public Result<UserMchListVO> createMchUser(@Valid @RequestBody CreateMchUserDTO dto) {
		UserMchListVO result = userService.createMchUser(dto);
		return Result.ok(result);
	}

	@PostMapping("/rider-user/create")
	@Operation(summary = "创建骑手用户", description = "创建新的骑手用户，同时插入base_user表和user_rider表。需要提供用户名、密码、手机号、真实姓名、身份证号等必填信息，支持分配角色")
	public Result<UserRiderListVO> createRiderUser(@Valid @RequestBody CreateRiderUserDTO dto) {
		UserRiderListVO result = userService.createRiderUser(dto);
		return Result.ok(result);
	}

	@PostMapping("/sys-user/create")
	@Operation(summary = "创建系统用户", description = "创建新的系统用户，同时插入base_user表和user_sys表。需要提供用户名、密码、手机号等必填信息，支持分配角色")
	public Result<UserSysListVO> createSysUser(@Valid @RequestBody CreateSysUserDTO dto) {
		UserSysListVO result = userService.createSysUser(dto);
		return Result.ok(result);
	}

	@PostMapping("/partner-user/create")
	@Operation(summary = "创建合伙人用户", description = "创建新的合伙人用户，同时插入base_user表和user_partner表。需要提供用户名、密码、手机号、合伙人姓名等必填信息，支持分配角色")
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
	@Operation(summary = "根据基础用户ID查询商家信息", description = "根据基础用户ID查询商家的详细信息，供内部服务调用")
	public Result<MchInfoDTO> getMchInfoByBaseUserId(@PathVariable Long baseUserId) {
		MchInfoDTO mchInfo = userService.getMchInfoByBaseUserId(baseUserId);
		if (mchInfo == null) {
			return Result.failed("商家信息不存在");
		}
		return Result.ok(mchInfo);
	}

	@PostMapping("/api/mch/batch")
	@Inner
	@Operation(summary = "批量查询商家信息", description = "根据基础用户ID列表批量查询商家的详细信息，供内部服务调用")
	public Result<List<MchInfoDTO>> batchGetMchInfo(@RequestBody List<Long> baseUserIds) {
		List<MchInfoDTO> mchInfoList = userService.batchGetMchInfo(baseUserIds);
		return Result.ok(mchInfoList);
	}

	@PostMapping("/api/user/balance/update")
	@Inner
	@Operation(summary = "更新用户余额或累计总收入", description = "更新用户（商家/骑手/合伙人）的余额或累计总收入，供内部服务调用")
	public Result<Void> updateUserBalance(@Valid @RequestBody MerchantBalanceUpdateDTO dto) {
		userService.updateUserBalance(dto);
		return Result.ok();
	}
}
