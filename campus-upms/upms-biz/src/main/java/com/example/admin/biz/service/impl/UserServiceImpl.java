package com.example.admin.biz.service.impl;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.admin.api.dto.*;
import com.example.admin.api.entity.*;
import com.example.admin.biz.mapper.BaseUserMapper;
import com.example.admin.biz.mapper.RoleMapper;
import com.example.admin.biz.mapper.SysSchoolMapper;
import com.example.admin.biz.mapper.UserAppMapper;
import com.example.admin.biz.mapper.UserMchMapper;
import com.example.admin.biz.mapper.UserPartnerMapper;
import com.example.admin.biz.mapper.UserRiderMapper;
import com.example.admin.biz.mapper.UserRoleMapper;
import com.example.admin.biz.mapper.UserSysMapper;
import com.example.admin.biz.service.UserService;
import com.example.admin.api.vo.UserAppListVO;
import com.example.admin.api.vo.UserMchListVO;
import com.example.admin.api.vo.UserPartnerListVO;
import com.example.admin.api.vo.UserRiderListVO;
import com.example.admin.api.vo.UserSysListVO;
import com.example.admin.api.vo.AbstractUserVO;
import com.example.common.core.enums.Gender;
import com.example.common.core.enums.UserType;
import com.example.common.core.exception.BusinessException;
import com.example.common.file.core.FileTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final BaseUserMapper baseUserMapper;
	private final UserRoleMapper userRoleMapper;
	private final RoleMapper roleMapper;
	private final UserAppMapper userAppMapper;
	private final UserMchMapper userMchMapper;
	private final UserRiderMapper userRiderMapper;
	private final UserPartnerMapper userPartnerMapper;
	private final UserSysMapper userSysMapper;
	private final SysSchoolMapper sysSchoolMapper;
	private final FileTemplate fileTemplate;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserInfo getUserInfo(String username) {
		if (!StringUtils.hasText(username)) {
			throw new BusinessException("INVALID_PARAM", "用户名不能为空");
		}

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

	@Override
	public UserInfo getUserInfoById(Long id) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
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

	@Override
	public Page<UserAppListVO> listAppUsers(UserQueryDTO queryDTO) {
		validatePageParams(queryDTO.getPage(), queryDTO.getSize());

		Page<UserAppListVO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

		LambdaQueryWrapper<BaseUser> userWrapper = buildUserWrapper(queryDTO, UserType.APP.getCode());

		IPage<BaseUser> userPage = baseUserMapper.selectPage(new Page<>(queryDTO.getPage(), queryDTO.getSize()), userWrapper);

		if (userPage.getRecords().isEmpty()) {
			return page;
		}

		List<Long> userIds = userPage.getRecords().stream()
				.map(BaseUser::getId)
				.collect(Collectors.toList());

		Map<Long, UserApp> userAppMap = batchQueryUserApp(userIds);
		Map<Long, SysSchool> schoolMap = batchQuerySchools(userAppMap.values());
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(userIds);

		List<UserAppListVO> voList = userPage.getRecords().stream().map(user -> {
			UserAppListVO vo = new UserAppListVO();
			vo.setId(user.getId());
			vo.setUsername(user.getUsername());
			vo.setPhone(maskPhone(user.getPhone()));
			vo.setAvatar(user.getAvatar());
			vo.setStatus(user.getStatus());
			vo.setCreateTime(user.getCreateAt());
			vo.setUserType(user.getUserType());

			fillAppUserDetail(vo, user.getId(), userAppMap, schoolMap);
			fillRoleInfo(vo, user.getId(), userRoleMap);

			return vo;
		}).collect(Collectors.toList());

		page.setRecords(voList);
		page.setTotal(userPage.getTotal());

		return page;
	}

	@Override
	public Page<UserMchListVO> listMchUsers(UserQueryDTO queryDTO) {
		validatePageParams(queryDTO.getPage(), queryDTO.getSize());

		Page<UserMchListVO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

		LambdaQueryWrapper<BaseUser> userWrapper = buildUserWrapper(queryDTO, UserType.MERCHANT.getCode());

		IPage<BaseUser> userPage = baseUserMapper.selectPage(new Page<>(queryDTO.getPage(), queryDTO.getSize()), userWrapper);

		if (userPage.getRecords().isEmpty()) {
			return page;
		}

		List<Long> userIds = userPage.getRecords().stream()
				.map(BaseUser::getId)
				.collect(Collectors.toList());

		Map<Long, UserMch> userMchMap = batchQueryUserMch(userIds);
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(userIds);

		List<UserMchListVO> voList = userPage.getRecords().stream().map(user -> {
			UserMchListVO vo = new UserMchListVO();
			vo.setId(user.getId());
			vo.setUsername(user.getUsername());
			vo.setPhone(maskPhone(user.getPhone()));
			vo.setAvatar(user.getAvatar());
			vo.setStatus(user.getStatus());
			vo.setCreateTime(user.getCreateAt());
			vo.setUserType(user.getUserType());

			fillMchUserDetail(vo, user.getId(), userMchMap);
			fillRoleInfo(vo, user.getId(), userRoleMap);

			return vo;
		}).collect(Collectors.toList());

		page.setRecords(voList);
		page.setTotal(userPage.getTotal());

		return page;
	}

	@Override
	public Page<UserRiderListVO> listRiderUsers(UserQueryDTO queryDTO) {
		validatePageParams(queryDTO.getPage(), queryDTO.getSize());

		Page<UserRiderListVO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

		LambdaQueryWrapper<BaseUser> userWrapper = buildUserWrapper(queryDTO, UserType.RIDER.getCode());

		IPage<BaseUser> userPage = baseUserMapper.selectPage(new Page<>(queryDTO.getPage(), queryDTO.getSize()), userWrapper);

		if (userPage.getRecords().isEmpty()) {
			return page;
		}

		List<Long> userIds = userPage.getRecords().stream()
				.map(BaseUser::getId)
				.collect(Collectors.toList());

		Map<Long, UserRider> userRiderMap = batchQueryUserRider(userIds);
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(userIds);

		List<UserRiderListVO> voList = userPage.getRecords().stream().map(user -> {
			UserRiderListVO vo = new UserRiderListVO();
			vo.setId(user.getId());
			vo.setUsername(user.getUsername());
			vo.setPhone(maskPhone(user.getPhone()));
			vo.setAvatar(user.getAvatar());
			vo.setStatus(user.getStatus());
			vo.setCreateTime(user.getCreateAt());
			vo.setUserType(user.getUserType());

			fillRiderUserDetail(vo, user.getId(), userRiderMap);
			fillRoleInfo(vo, user.getId(), userRoleMap);

			return vo;
		}).collect(Collectors.toList());

		page.setRecords(voList);
		page.setTotal(userPage.getTotal());

		return page;
	}

	@Override
	public Page<UserSysListVO> listSysUsers(UserQueryDTO queryDTO) {
		validatePageParams(queryDTO.getPage(), queryDTO.getSize());

		Page<UserSysListVO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

		LambdaQueryWrapper<BaseUser> userWrapper = buildUserWrapper(queryDTO, UserType.SYSTEM.getCode());

		IPage<BaseUser> userPage = baseUserMapper.selectPage(new Page<>(queryDTO.getPage(), queryDTO.getSize()), userWrapper);

		if (userPage.getRecords().isEmpty()) {
			return page;
		}

		List<Long> userIds = userPage.getRecords().stream()
				.map(BaseUser::getId)
				.collect(Collectors.toList());

		Map<Long, UserSys> userSysMap = batchQueryUserSys(userIds);
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(userIds);

		List<UserSysListVO> voList = userPage.getRecords().stream().map(user -> {
			UserSysListVO vo = new UserSysListVO();
			vo.setId(user.getId());
			vo.setUsername(user.getUsername());
			vo.setPhone(maskPhone(user.getPhone()));
			vo.setAvatar(user.getAvatar());
			vo.setNickname(user.getNickname());
			vo.setEmail(user.getEmail());
			vo.setStatus(user.getStatus());
			vo.setCreateTime(user.getCreateAt());
			vo.setLastLoginAt(user.getLastLoginAt());
			vo.setUserType(user.getUserType());

			fillSysUserDetail(vo, user.getId(), userSysMap);
			fillRoleInfo(vo, user.getId(), userRoleMap);

			return vo;
		}).collect(Collectors.toList());

		page.setRecords(voList);
		page.setTotal(userPage.getTotal());

		return page;
	}

	@Override
	public Page<UserPartnerListVO> listPartnerUsers(UserQueryDTO queryDTO) {
		validatePageParams(queryDTO.getPage(), queryDTO.getSize());

		Page<UserPartnerListVO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

		LambdaQueryWrapper<BaseUser> userWrapper = buildUserWrapper(queryDTO, UserType.PARTNER.getCode());

		IPage<BaseUser> userPage = baseUserMapper.selectPage(new Page<>(queryDTO.getPage(), queryDTO.getSize()), userWrapper);

		if (userPage.getRecords().isEmpty()) {
			return page;
		}

		List<Long> userIds = userPage.getRecords().stream()
				.map(BaseUser::getId)
				.collect(Collectors.toList());

		Map<Long, UserPartner> userPartnerMap = batchQueryUserPartner(userIds);
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(userIds);

		List<UserPartnerListVO> voList = userPage.getRecords().stream().map(user -> {
			UserPartnerListVO vo = new UserPartnerListVO();
			vo.setId(user.getId());
			vo.setUsername(user.getUsername());
			vo.setPhone(maskPhone(user.getPhone()));
			vo.setAvatar(user.getAvatar());
			vo.setStatus(user.getStatus());
			vo.setCreateTime(user.getCreateAt());
			vo.setUserType(user.getUserType());

			fillPartnerUserDetail(vo, user.getId(), userPartnerMap);
			fillRoleInfo(vo, user.getId(), userRoleMap);

			return vo;
		}).collect(Collectors.toList());

		page.setRecords(voList);
		page.setTotal(userPage.getTotal());

		return page;
	}

	private LambdaQueryWrapper<BaseUser> buildUserWrapper(UserQueryDTO queryDTO, Integer userType) {
		LambdaQueryWrapper<BaseUser> userWrapper = Wrappers.lambdaQuery();
		userWrapper.eq(BaseUser::getUserType, userType);

		if (StringUtils.hasText(queryDTO.getUsername())) {
			userWrapper.like(BaseUser::getUsername, queryDTO.getUsername());
		}

		if (StringUtils.hasText(queryDTO.getPhone())) {
			userWrapper.like(BaseUser::getPhone, queryDTO.getPhone());
		}

		if (queryDTO.getStatus() != null) {
			userWrapper.eq(BaseUser::getStatus, queryDTO.getStatus());
		}

		if (StringUtils.hasText(queryDTO.getStartTime())) {
			userWrapper.ge(BaseUser::getCreateAt, queryDTO.getStartTime());
		}

		if (StringUtils.hasText(queryDTO.getEndTime())) {
			userWrapper.le(BaseUser::getCreateAt, queryDTO.getEndTime());
		}

		return userWrapper;
	}

	private Map<Long, UserApp> batchQueryUserApp(List<Long> userIds) {
		if (userIds == null || userIds.isEmpty()) {
			return Collections.emptyMap();
		}
		LambdaQueryWrapper<UserApp> appWrapper = Wrappers.lambdaQuery();
		appWrapper.in(UserApp::getBaseUserId, userIds);
		return userAppMapper.selectList(appWrapper).stream()
				.collect(Collectors.toMap(UserApp::getBaseUserId, Function.identity(), (v1, v2) -> v1));
	}

	private Map<Long, SysSchool> batchQuerySchools(Collection<UserApp> userApps) {
		Set<Long> schoolIds = userApps.stream()
				.map(UserApp::getSchoolId)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());

		if (schoolIds.isEmpty()) {
			return Collections.emptyMap();
		}

		return sysSchoolMapper.selectBatchIds(schoolIds).stream()
				.collect(Collectors.toMap(SysSchool::getId, Function.identity()));
	}

	private Map<Long, UserMch> batchQueryUserMch(List<Long> userIds) {
		if (userIds == null || userIds.isEmpty()) {
			return Collections.emptyMap();
		}
		LambdaQueryWrapper<UserMch> mchWrapper = Wrappers.lambdaQuery();
		mchWrapper.in(UserMch::getBaseUserId, userIds);
		return userMchMapper.selectList(mchWrapper).stream()
				.collect(Collectors.toMap(UserMch::getBaseUserId, Function.identity(), (v1, v2) -> v1));
	}

	private Map<Long, UserRider> batchQueryUserRider(List<Long> userIds) {
		if (userIds == null || userIds.isEmpty()) {
			return Collections.emptyMap();
		}
		LambdaQueryWrapper<UserRider> riderWrapper = Wrappers.lambdaQuery();
		riderWrapper.in(UserRider::getBaseUserId, userIds);
		return userRiderMapper.selectList(riderWrapper).stream()
				.collect(Collectors.toMap(UserRider::getBaseUserId, Function.identity(), (v1, v2) -> v1));
	}

	private Map<Long, UserPartner> batchQueryUserPartner(List<Long> userIds) {
		if (userIds == null || userIds.isEmpty()) {
			return Collections.emptyMap();
		}
		LambdaQueryWrapper<UserPartner> partnerWrapper = Wrappers.lambdaQuery();
		partnerWrapper.in(UserPartner::getBaseUserId, userIds);
		return userPartnerMapper.selectList(partnerWrapper).stream()
				.collect(Collectors.toMap(UserPartner::getBaseUserId, Function.identity(), (v1, v2) -> v1));
	}

	private Map<Long, UserSys> batchQueryUserSys(List<Long> userIds) {
		if (userIds == null || userIds.isEmpty()) {
			return Collections.emptyMap();
		}
		LambdaQueryWrapper<UserSys> sysWrapper = Wrappers.lambdaQuery();
		sysWrapper.in(UserSys::getBaseUserId, userIds);
		return userSysMapper.selectList(sysWrapper).stream()
				.collect(Collectors.toMap(UserSys::getBaseUserId, Function.identity(), (v1, v2) -> v1));
	}

	private Map<Long, List<Role>> batchQueryUserRoles(List<Long> userIds) {
		if (userIds == null || userIds.isEmpty()) {
			return Collections.emptyMap();
		}
		LambdaQueryWrapper<UserRole> roleWrapper = Wrappers.lambdaQuery();
		roleWrapper.in(UserRole::getUserId, userIds);
		List<UserRole> userRoles = userRoleMapper.selectList(roleWrapper);

		if (userRoles.isEmpty()) {
			return Collections.emptyMap();
		}

		List<Long> roleIds = userRoles.stream()
				.map(UserRole::getRoleId)
				.distinct()
				.collect(Collectors.toList());

		Map<Long, Role> roleMap = roleMapper.selectBatchIds(roleIds).stream()
				.collect(Collectors.toMap(Role::getId, Function.identity()));

		return userRoles.stream()
				.collect(Collectors.groupingBy(UserRole::getUserId,
						Collectors.mapping(ur -> roleMap.get(ur.getRoleId()), Collectors.toList())));
	}

	private void fillAppUserDetail(UserAppListVO vo, Long userId, Map<Long, UserApp> userAppMap, Map<Long, SysSchool> schoolMap) {
		UserApp userApp = userAppMap.get(userId);
		if (userApp != null) {
			vo.setCode(userApp.getStuCode());
			vo.setGender(Gender.getText(userApp.getGender()));
			vo.setBalance(userApp.getBalance());
			vo.setCommissionTotal(userApp.getTotalAmount());
			vo.setRealName(userApp.getRealName());

			SysSchool school = schoolMap.get(userApp.getSchoolId());
			if (school != null) {
				vo.setSchoolName(school.getSchoolName());
			}
		}
	}

	private void fillMchUserDetail(UserMchListVO vo, Long userId, Map<Long, UserMch> userMchMap) {
		UserMch userMch = userMchMap.get(userId);
		if (userMch != null) {
			vo.setMchName(userMch.getMchName());
			vo.setLogo(userMch.getLogo());
			vo.setContactName(userMch.getContactName());
			vo.setIsOpen(userMch.getIsOpen());
			vo.setMinimumOrderAmount(userMch.getMinimumOrderAmount());
			vo.setBusinessStartTime(userMch.getBusinessStartTime());
			vo.setBusinessEndTime(userMch.getBusinessEndTime());
		}
	}

	private void fillRiderUserDetail(UserRiderListVO vo, Long userId, Map<Long, UserRider> userRiderMap) {
		UserRider userRider = userRiderMap.get(userId);
		if (userRider != null) {
			vo.setGender(Gender.getText(userRider.getGender()));
			vo.setRealName(userRider.getRealName());
			vo.setBalance(userRider.getBalance());
			vo.setCommissionTotal(userRider.getCommissionTotal());
			vo.setEmergencyContactName(userRider.getEmergencyContactName());
			vo.setEmergencyContactPhone(maskPhone(userRider.getEmergencyContactPhone()));
		}
	}

	private void fillSysUserDetail(UserSysListVO vo, Long userId, Map<Long, UserSys> userSysMap) {
		UserSys userSys = userSysMap.get(userId);
		if (userSys != null) {
			vo.setRealName(userSys.getRealName());
			vo.setGender(Gender.getText(userSys.getGender()));
		}
	}

	private void fillPartnerUserDetail(UserPartnerListVO vo, Long userId, Map<Long, UserPartner> userPartnerMap) {
		UserPartner userPartner = userPartnerMap.get(userId);
		if (userPartner != null) {
			vo.setPartnerName(userPartner.getPartnerName());
			vo.setInviteCode(userPartner.getInviteCode());
			vo.setInviteCodePath(userPartner.getInviteCodePath());
			vo.setCardNumber(maskCardNumber(userPartner.getCardNumber()));
			vo.setCommissionRate(userPartner.getCommissionRate());
			vo.setParentId(userPartner.getParentId());
		}
	}

	private void fillRoleInfo(AbstractUserVO vo, Long userId, Map<Long, List<Role>> userRoleMap) {
		List<Role> roles = userRoleMap.get(userId);
		if (roles != null && !roles.isEmpty()) {
			vo.setRoleId(String.valueOf(roles.get(0).getId()));
			vo.setRoleName(roles.get(0).getRoleName());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateUserStatus(Long id, UserStatusDTO statusDTO) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}
		if (statusDTO == null || statusDTO.getStatus() == null) {
			throw new BusinessException("INVALID_PARAM", "用户状态不能为空");
		}

		BaseUser user = baseUserMapper.selectById(id);
		if (user == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		user.setStatus(statusDTO.getStatus());
		baseUserMapper.updateById(user);

		log.info("用户状态更新成功，用户ID：{}，新状态：{}", id, statusDTO.getStatus());
	}

	private String maskPhone(String phone) {
		if (!StringUtils.hasText(phone) || phone.length() < 7) {
			return phone;
		}
		return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
	}

	private String maskCardNumber(String cardNumber) {
		if (!StringUtils.hasText(cardNumber) || cardNumber.length() < 8) {
			return cardNumber;
		}
		return cardNumber.substring(0, 4) + "********" + cardNumber.substring(cardNumber.length() - 4);
	}

	private String maskIdCard(String idCard) {
		if (!StringUtils.hasText(idCard) || idCard.length() < 8) {
			return idCard;
		}
		return idCard.substring(0, 3) + "***********" + idCard.substring(idCard.length() - 4);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateUser(Long id, Integer userType, Object updateDTO) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}
		if (userType == null) {
			throw new BusinessException("INVALID_PARAM", "用户类型不能为空");
		}
		if (updateDTO == null) {
			throw new BusinessException("INVALID_PARAM", "更新数据不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		UserType type = UserType.getByCode(userType);
		if (type == null) {
			throw new BusinessException("INVALID_USER_TYPE", "不支持的用户类型");
		}

		switch (type) {
			case APP:
				updateAppUser(baseUser, (UpdateUserAppDTO) updateDTO);
				break;
			case MERCHANT:
				updateMchUser(baseUser, (UpdateUserMchDTO) updateDTO);
				break;
			case RIDER:
				updateRiderUser(baseUser, (UpdateUserRiderDTO) updateDTO);
				break;
			case SYSTEM:
				updateSysUser(baseUser, (UpdateUserSysDTO) updateDTO);
				break;
			case PARTNER:
				updatePartnerUser(baseUser, (UpdateUserPartnerDTO) updateDTO);
				break;
			default:
				throw new BusinessException("INVALID_USER_TYPE", "不支持的用户类型");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateSysUser(Long id, UpdateUserSysDTO dto) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}
		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "更新数据不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		updateSysUser(baseUser, dto);

		// 更新用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			// 删除用户原有的所有角色关联
			LambdaQueryWrapper<UserRole> roleWrapper = Wrappers.lambdaQuery();
			roleWrapper.eq(UserRole::getUserId, id);
			userRoleMapper.delete(roleWrapper);

			// 批量创建新的角色关联
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(id);
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("系统用户角色更新成功，用户ID：{}，角色ID列表：{}", id, dto.getRoleIds());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateAppUser(Long id, UpdateUserAppDTO dto) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}
		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "更新数据不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		updateAppUser(baseUser, dto);

		// 更新用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			// 删除用户原有的所有角色关联
			LambdaQueryWrapper<UserRole> roleWrapper = Wrappers.lambdaQuery();
			roleWrapper.eq(UserRole::getUserId, id);
			userRoleMapper.delete(roleWrapper);

			// 批量创建新的角色关联
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(id);
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("C端用户角色更新成功，用户ID：{}，角色ID列表：{}", id, dto.getRoleIds());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMchUser(Long id, UpdateUserMchDTO dto) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}
		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "更新数据不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		updateMchUser(baseUser, dto);

		// 更新用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			// 删除用户原有的所有角色关联
			LambdaQueryWrapper<UserRole> roleWrapper = Wrappers.lambdaQuery();
			roleWrapper.eq(UserRole::getUserId, id);
			userRoleMapper.delete(roleWrapper);

			// 批量创建新的角色关联
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(id);
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("商家用户角色更新成功，用户ID：{}，角色ID列表：{}", id, dto.getRoleIds());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRiderUser(Long id, UpdateUserRiderDTO dto) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}
		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "更新数据不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		updateRiderUser(baseUser, dto);

		// 更新用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			// 删除用户原有的所有角色关联
			LambdaQueryWrapper<UserRole> roleWrapper = Wrappers.lambdaQuery();
			roleWrapper.eq(UserRole::getUserId, id);
			userRoleMapper.delete(roleWrapper);

			// 批量创建新的角色关联
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(id);
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("骑手用户角色更新成功，用户ID：{}，角色ID列表：{}", id, dto.getRoleIds());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePartnerUser(Long id, UpdateUserPartnerDTO dto) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}
		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "更新数据不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		updatePartnerUser(baseUser, dto);

		// 更新用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			// 删除用户原有的所有角色关联
			LambdaQueryWrapper<UserRole> roleWrapper = Wrappers.lambdaQuery();
			roleWrapper.eq(UserRole::getUserId, id);
			userRoleMapper.delete(roleWrapper);

			// 批量创建新的角色关联
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(id);
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("合伙人用户角色更新成功，用户ID：{}，角色ID列表：{}", id, dto.getRoleIds());
		}
	}

	private void updateAppUser(BaseUser baseUser, UpdateUserAppDTO dto) {
		if (StringUtils.hasText(dto.getUsername())) {
			baseUser.setUsername(dto.getUsername());
		}
		if (StringUtils.hasText(dto.getPhone())) {
			validatePhone(dto.getPhone());
			baseUser.setPhone(dto.getPhone());
		}
		if (StringUtils.hasText(dto.getAvatar())) {
			baseUser.setAvatar(dto.getAvatar());
		}
		if (dto.getStatus() != null) {
			baseUser.setStatus(dto.getStatus());
		}
		baseUserMapper.updateById(baseUser);

		LambdaQueryWrapper<UserApp> appWrapper = Wrappers.lambdaQuery();
		appWrapper.eq(UserApp::getBaseUserId, baseUser.getId());
		UserApp userApp = userAppMapper.selectOne(appWrapper);

		if (userApp != null) {
			if (dto.getSchoolId() != null) {
				userApp.setSchoolId(dto.getSchoolId());
			}
			if (StringUtils.hasText(dto.getRealName())) {
				userApp.setRealName(dto.getRealName());
			}
			if (dto.getGender() != null) {
				userApp.setGender(dto.getGender());
			}
			if (StringUtils.hasText(dto.getStuCode())) {
				userApp.setStuCode(dto.getStuCode());
			}
			userAppMapper.updateById(userApp);
		}
	}

	private void updateMchUser(BaseUser baseUser, UpdateUserMchDTO dto) {
		if (StringUtils.hasText(dto.getUsername())) {
			baseUser.setUsername(dto.getUsername());
		}
		if (StringUtils.hasText(dto.getPhone())) {
			validatePhone(dto.getPhone());
			baseUser.setPhone(dto.getPhone());
		}
		if (StringUtils.hasText(dto.getAvatar())) {
			baseUser.setAvatar(dto.getAvatar());
		}
		if (dto.getStatus() != null) {
			baseUser.setStatus(dto.getStatus());
		}
		if (StringUtils.hasText(dto.getPassword())) {
			baseUser.setPassword(dto.getPassword());
		}
		baseUserMapper.updateById(baseUser);

		LambdaQueryWrapper<UserMch> mchWrapper = Wrappers.lambdaQuery();
		mchWrapper.eq(UserMch::getBaseUserId, baseUser.getId());
		UserMch userMch = userMchMapper.selectOne(mchWrapper);

		if (userMch != null) {
			if (StringUtils.hasText(dto.getShopName())) {
				userMch.setMchName(dto.getShopName());
			}
			if (StringUtils.hasText(dto.getBusinessLicenseUrls())) {
				userMch.setBusinessLicenseUrls(dto.getBusinessLicenseUrls());
			}
			if (StringUtils.hasText(dto.getPaymentAccount())) {
				userMch.setCardNumber(dto.getPaymentAccount());
			}
			if (StringUtils.hasText(dto.getBusinessStartTime())) {
				userMch.setBusinessStartTime(LocalTime.parse(dto.getBusinessStartTime()));
			}
			if (StringUtils.hasText(dto.getBusinessEndTime())) {
				userMch.setBusinessEndTime(LocalTime.parse(dto.getBusinessEndTime()));
			}
			if (StringUtils.hasText(dto.getLogo())) {
				userMch.setLogo(dto.getLogo());
			}
			if (StringUtils.hasText(dto.getContactName())) {
				userMch.setContactName(dto.getContactName());
			}
			if (StringUtils.hasText(dto.getIdCard())) {
				userMch.setIdCard(dto.getIdCard());
			}
			if (dto.getPartnerId() != null) {
				userMch.setPartnerId(dto.getPartnerId());
			}
			if (dto.getMinimumOrderAmount() != null) {
				userMch.setMinimumOrderAmount(dto.getMinimumOrderAmount());
			}
			userMchMapper.updateById(userMch);
		}
	}

	private void updateRiderUser(BaseUser baseUser, UpdateUserRiderDTO dto) {
		if (StringUtils.hasText(dto.getUsername())) {
			baseUser.setUsername(dto.getUsername());
		}
		if (StringUtils.hasText(dto.getPhone())) {
			validatePhone(dto.getPhone());
			baseUser.setPhone(dto.getPhone());
		}
		if (StringUtils.hasText(dto.getAvatar())) {
			baseUser.setAvatar(dto.getAvatar());
		}
		if (dto.getStatus() != null) {
			baseUser.setStatus(dto.getStatus());
		}
		baseUserMapper.updateById(baseUser);

		LambdaQueryWrapper<UserRider> riderWrapper = Wrappers.lambdaQuery();
		riderWrapper.eq(UserRider::getBaseUserId, baseUser.getId());
		UserRider userRider = userRiderMapper.selectOne(riderWrapper);

		if (userRider != null) {
			if (StringUtils.hasText(dto.getRiderName())) {
				userRider.setRealName(dto.getRiderName());
			}
			if (StringUtils.hasText(dto.getIdCard())) {
				userRider.setIdCard(dto.getIdCard());
			}
			if (dto.getGender() != null) {
				userRider.setGender(dto.getGender());
			}
			if (StringUtils.hasText(dto.getCardNumber())) {
				userRider.setCardNumber(dto.getCardNumber());
			}
			if (StringUtils.hasText(dto.getEmergencyContactName())) {
				userRider.setEmergencyContactName(dto.getEmergencyContactName());
			}
			if (StringUtils.hasText(dto.getEmergencyContactPhone())) {
				userRider.setEmergencyContactPhone(dto.getEmergencyContactPhone());
			}
			if (StringUtils.hasText(dto.getIdCardFrontUrl())) {
				userRider.setIdCardFront(dto.getIdCardFrontUrl());
			}
			if (StringUtils.hasText(dto.getIdCardBackUrl())) {
				userRider.setIdCardBack(dto.getIdCardBackUrl());
			}
			userRiderMapper.updateById(userRider);
		}
	}

	private void updateSysUser(BaseUser baseUser, UpdateUserSysDTO dto) {
		if (StringUtils.hasText(dto.getUsername())) {
			baseUser.setUsername(dto.getUsername());
		}
		if (StringUtils.hasText(dto.getPhone())) {
			validatePhone(dto.getPhone());
			baseUser.setPhone(dto.getPhone());
		}
		if (StringUtils.hasText(dto.getAvatar())) {
			baseUser.setAvatar(dto.getAvatar());
		}
		if (dto.getStatus() != null) {
			baseUser.setStatus(dto.getStatus());
		}
		if (StringUtils.hasText(dto.getEmail())) {
			validateEmail(dto.getEmail());
			baseUser.setEmail(dto.getEmail());
		}
		baseUserMapper.updateById(baseUser);

		LambdaQueryWrapper<UserSys> sysWrapper = Wrappers.lambdaQuery();
		sysWrapper.eq(UserSys::getBaseUserId, baseUser.getId());
		UserSys userSys = userSysMapper.selectOne(sysWrapper);

		if (userSys != null) {
			if (StringUtils.hasText(dto.getRealName())) {
				userSys.setRealName(dto.getRealName());
			}
			if (dto.getGender() != null) {
				userSys.setGender(dto.getGender());
			}
			userSysMapper.updateById(userSys);
		}
	}

	private void updatePartnerUser(BaseUser baseUser, UpdateUserPartnerDTO dto) {
		if (StringUtils.hasText(dto.getUsername())) {
			baseUser.setUsername(dto.getUsername());
		}
		if (StringUtils.hasText(dto.getPhone())) {
			validatePhone(dto.getPhone());
			baseUser.setPhone(dto.getPhone());
		}
		if (StringUtils.hasText(dto.getAvatar())) {
			baseUser.setAvatar(dto.getAvatar());
		}
		if (dto.getStatus() != null) {
			baseUser.setStatus(dto.getStatus());
		}
		baseUserMapper.updateById(baseUser);

		LambdaQueryWrapper<UserPartner> partnerWrapper = Wrappers.lambdaQuery();
		partnerWrapper.eq(UserPartner::getBaseUserId, baseUser.getId());
		UserPartner userPartner = userPartnerMapper.selectOne(partnerWrapper);

		if (userPartner != null) {
			if (StringUtils.hasText(dto.getPartnerName())) {
				userPartner.setPartnerName(dto.getPartnerName());
			}
			if (StringUtils.hasText(dto.getCardNumber())) {
				userPartner.setCardNumber(dto.getCardNumber());
			}
			if (dto.getCommissionRate() != null) {
				userPartner.setCommissionRate(dto.getCommissionRate());
			}
			if (dto.getParentId() != null) {
				userPartner.setParentId(dto.getParentId());
			}
			userPartnerMapper.updateById(userPartner);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void resetUserPassword(Long id, ResetPasswordDTO resetPasswordDTO) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}
		if (resetPasswordDTO == null || !StringUtils.hasText(resetPasswordDTO.getNewPassword())) {
			throw new BusinessException("INVALID_PARAM", "新密码不能为空");
		}

		String password = resetPasswordDTO.getNewPassword();
		validatePassword(password);

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		baseUser.setPassword(password);
		baseUserMapper.updateById(baseUser);

		log.info("用户密码重置成功，用户ID：{}", id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteUser(Long id) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		baseUserMapper.deleteById(id);

		LambdaQueryWrapper<UserRole> roleWrapper = Wrappers.lambdaQuery();
		roleWrapper.eq(UserRole::getUserId, id);
		userRoleMapper.delete(roleWrapper);

		log.info("用户删除成功，用户ID：{}", id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserAppListVO createAppUser(CreateAppUserDTO dto) {
		// 校验用户名唯一性
		LambdaQueryWrapper<BaseUser> usernameWrapper = Wrappers.lambdaQuery();
		usernameWrapper.eq(BaseUser::getUsername, dto.getUsername());
		if (baseUserMapper.selectCount(usernameWrapper) > 0) {
			throw new BusinessException("USERNAME_EXISTS", "用户名已存在");
		}

		// 校验手机号唯一性
		LambdaQueryWrapper<BaseUser> phoneWrapper = Wrappers.lambdaQuery();
		phoneWrapper.eq(BaseUser::getPhone, dto.getPhone());
		if (baseUserMapper.selectCount(phoneWrapper) > 0) {
			throw new BusinessException("PHONE_EXISTS", "手机号已存在");
		}

		// 密码加密处理
		String encodedPassword = passwordEncoder.encode(dto.getPassword());

		// 插入base_user表
		BaseUser baseUser = new BaseUser();
		baseUser.setUsername(dto.getUsername());
		baseUser.setPassword(encodedPassword);
		baseUser.setPhone(dto.getPhone());
		baseUser.setEmail(dto.getEmail());
		baseUser.setNickname(dto.getNickname());
		baseUser.setAvatar(dto.getAvatar());
		baseUser.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);
		baseUser.setUserType(UserType.APP.getCode());
		baseUserMapper.insert(baseUser);

		// 插入user_app表
		UserApp userApp = new UserApp();
		userApp.setBaseUserId(baseUser.getId());
		userApp.setRealName(dto.getRealName());
		userApp.setGender(dto.getGender());
		userApp.setSchoolId(dto.getSchoolId());
		userApp.setStuCode(dto.getStuCode());
		userApp.setBalance(BigDecimal.ZERO);
		userApp.setTotalAmount(BigDecimal.ZERO);
		userAppMapper.insert(userApp);

		log.info("C端用户创建成功，用户ID：{}，用户名：{}", baseUser.getId(), dto.getUsername());

		// 创建用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(baseUser.getId());
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("C端用户角色关联创建成功，用户ID：{}，角色ID列表：{}", baseUser.getId(), dto.getRoleIds());
		}

		// 构建并返回UserAppListVO
		UserAppListVO vo = new UserAppListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(maskPhone(baseUser.getPhone()));
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setRealName(userApp.getRealName());
		vo.setGender(Gender.getText(userApp.getGender()));
		vo.setCode(userApp.getStuCode());
		vo.setBalance(userApp.getBalance());
		vo.setCommissionTotal(userApp.getTotalAmount());
		vo.setCreateTime(baseUser.getCreateAt());

		// 获取学校名称
		if (userApp.getSchoolId() != null) {
			SysSchool school = sysSchoolMapper.selectById(userApp.getSchoolId());
			if (school != null) {
				vo.setSchoolName(school.getSchoolName());
			}
		}

		return vo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserMchListVO createMchUser(CreateMchUserDTO dto) {
		// 校验用户名唯一性
		LambdaQueryWrapper<BaseUser> usernameWrapper = Wrappers.lambdaQuery();
		usernameWrapper.eq(BaseUser::getUsername, dto.getUsername());
		if (baseUserMapper.selectCount(usernameWrapper) > 0) {
			throw new BusinessException("USERNAME_EXISTS", "用户名已存在");
		}

		// 校验手机号唯一性
		LambdaQueryWrapper<BaseUser> phoneWrapper = Wrappers.lambdaQuery();
		phoneWrapper.eq(BaseUser::getPhone, dto.getPhone());
		if (baseUserMapper.selectCount(phoneWrapper) > 0) {
			throw new BusinessException("PHONE_EXISTS", "手机号已存在");
		}

		// 密码加密处理
		String encodedPassword = passwordEncoder.encode(dto.getPassword());

		// 插入base_user表
		BaseUser baseUser = new BaseUser();
		baseUser.setUsername(dto.getUsername());
		baseUser.setPassword(encodedPassword);
		baseUser.setPhone(dto.getPhone());
		baseUser.setEmail(dto.getEmail());
		baseUser.setNickname(dto.getNickname());
		baseUser.setAvatar(dto.getAvatar());
		baseUser.setStatus(dto.getStatus() != null ? dto.getStatus() : 0); //商家创建时暂时不启用
		baseUser.setUserType(UserType.MERCHANT.getCode());
		baseUserMapper.insert(baseUser);

		// 插入user_mch表
		UserMch userMch = new UserMch();
		userMch.setBaseUserId(baseUser.getId());
		userMch.setMchName(dto.getMchName());
		userMch.setContactName(dto.getContactName());
		userMch.setLogo(dto.getLogo());
		userMch.setBusinessLicenseUrls(dto.getBusinessLicenseUrls());
		userMch.setPartnerId(dto.getPartnerId());
		userMch.setIdCard(dto.getIdCard());
		userMch.setMinimumOrderAmount(dto.getMinimumOrderAmount() != null ? dto.getMinimumOrderAmount() : BigDecimal.ZERO);
		userMch.setCardNumber(dto.getCardNumber());
		userMch.setIsOpen(0);
		userMchMapper.insert(userMch);

		log.info("商家用户创建成功，用户ID：{}，用户名：{}，商户名：{}", baseUser.getId(), dto.getUsername(), dto.getMchName());

		// 创建用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(baseUser.getId());
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("商家用户角色关联创建成功，用户ID：{}，角色ID列表：{}", baseUser.getId(), dto.getRoleIds());
		}

		// 构建并返回UserMchListVO
		UserMchListVO vo = new UserMchListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(maskPhone(baseUser.getPhone()));
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setMchName(userMch.getMchName());
		vo.setContactName(userMch.getContactName());
		vo.setLogo(userMch.getLogo());
		vo.setIsOpen(userMch.getIsOpen());
		vo.setCreateTime(baseUser.getCreateAt());

		// 获取合伙人名称
		if (userMch.getPartnerId() != null) {
			UserPartner partner = userPartnerMapper.selectOne(
				Wrappers.lambdaQuery(UserPartner.class).eq(UserPartner::getBaseUserId, userMch.getPartnerId())
			);
			if (partner != null) {
				vo.setPartnerName(partner.getPartnerName());
			}
		}

		return vo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserRiderListVO createRiderUser(CreateRiderUserDTO dto) {
		// 校验用户名唯一性
		LambdaQueryWrapper<BaseUser> usernameWrapper = Wrappers.lambdaQuery();
		usernameWrapper.eq(BaseUser::getUsername, dto.getUsername());
		if (baseUserMapper.selectCount(usernameWrapper) > 0) {
			throw new BusinessException("USERNAME_EXISTS", "用户名已存在");
		}

		// 校验手机号唯一性
		LambdaQueryWrapper<BaseUser> phoneWrapper = Wrappers.lambdaQuery();
		phoneWrapper.eq(BaseUser::getPhone, dto.getPhone());
		if (baseUserMapper.selectCount(phoneWrapper) > 0) {
			throw new BusinessException("PHONE_EXISTS", "手机号已存在");
		}

		// 密码加密处理
		String encodedPassword = passwordEncoder.encode(dto.getPassword());

		// 插入base_user表
		BaseUser baseUser = new BaseUser();
		baseUser.setUsername(dto.getUsername());
		baseUser.setPassword(encodedPassword);
		baseUser.setPhone(dto.getPhone());
		baseUser.setEmail(dto.getEmail());
		baseUser.setNickname(dto.getNickname());
		baseUser.setAvatar(dto.getAvatar());
		baseUser.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);
		baseUser.setUserType(UserType.RIDER.getCode());
		baseUserMapper.insert(baseUser);

		// 插入user_rider表
		UserRider userRider = new UserRider();
		userRider.setBaseUserId(baseUser.getId());
		userRider.setRealName(dto.getRealName());
		userRider.setGender(dto.getGender());
		userRider.setIdCard(dto.getIdCard());
		userRider.setIdCardFront(dto.getIdCardFront());
		userRider.setIdCardBack(dto.getIdCardBack());
		userRider.setCardNumber(dto.getCardNumber());
		userRider.setEmergencyContactName(dto.getEmergencyContactName());
		userRider.setEmergencyContactPhone(dto.getEmergencyContactPhone());
		userRider.setBalance(BigDecimal.ZERO);
		userRider.setCommissionTotal(BigDecimal.ZERO);
		userRiderMapper.insert(userRider);

		log.info("骑手用户创建成功，用户ID：{}，用户名：{}，真实姓名：{}", baseUser.getId(), dto.getUsername(), dto.getRealName());

		// 创建用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(baseUser.getId());
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("骑手用户角色关联创建成功，用户ID：{}，角色ID列表：{}", baseUser.getId(), dto.getRoleIds());
		}

		// 构建并返回UserRiderListVO
		UserRiderListVO vo = new UserRiderListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(maskPhone(baseUser.getPhone()));
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setRealName(userRider.getRealName());
		vo.setGender(Gender.getText(userRider.getGender()));
		vo.setIdCard(maskIdCard(userRider.getIdCard()));
		vo.setEmergencyContactName(userRider.getEmergencyContactName());
		vo.setEmergencyContactPhone(userRider.getEmergencyContactPhone());
		vo.setBalance(userRider.getBalance());
		vo.setCommissionTotal(userRider.getCommissionTotal());
		vo.setCreateTime(baseUser.getCreateAt());

		return vo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserSysListVO createSysUser(CreateSysUserDTO dto) {
		// 校验用户名唯一性
		LambdaQueryWrapper<BaseUser> usernameWrapper = Wrappers.lambdaQuery();
		usernameWrapper.eq(BaseUser::getUsername, dto.getUsername());
		if (baseUserMapper.selectCount(usernameWrapper) > 0) {
			throw new BusinessException("USERNAME_EXISTS", "用户名已存在");
		}

		// 校验手机号唯一性
		LambdaQueryWrapper<BaseUser> phoneWrapper = Wrappers.lambdaQuery();
		phoneWrapper.eq(BaseUser::getPhone, dto.getPhone());
		if (baseUserMapper.selectCount(phoneWrapper) > 0) {
			throw new BusinessException("PHONE_EXISTS", "手机号已存在");
		}

		// 密码加密处理
		String encodedPassword = passwordEncoder.encode(dto.getPassword());

		// 插入base_user表
		BaseUser baseUser = new BaseUser();
		baseUser.setUsername(dto.getUsername());
		baseUser.setPassword(encodedPassword);
		baseUser.setPhone(dto.getPhone());
		baseUser.setEmail(dto.getEmail());
		baseUser.setNickname(dto.getNickname());
		baseUser.setAvatar(dto.getAvatar());
		baseUser.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);
		baseUser.setUserType(UserType.SYSTEM.getCode());
		baseUserMapper.insert(baseUser);

		// 插入user_sys表
		UserSys userSys = new UserSys();
		userSys.setBaseUserId(baseUser.getId());
		userSys.setRealName(dto.getRealName());
		userSys.setGender(dto.getGender());
		userSysMapper.insert(userSys);

		log.info("系统用户创建成功，用户ID：{}，用户名：{}，真实姓名：{}", baseUser.getId(), dto.getUsername(), dto.getRealName());

		// 创建用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(baseUser.getId());
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("系统用户角色关联创建成功，用户ID：{}，角色ID列表：{}", baseUser.getId(), dto.getRoleIds());
		}

		// 构建并返回UserSysListVO
		UserSysListVO vo = new UserSysListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(maskPhone(baseUser.getPhone()));
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setRealName(userSys.getRealName());
		vo.setGender(Gender.getText(userSys.getGender()));
		vo.setCreateTime(baseUser.getCreateAt());

		return vo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserPartnerListVO createPartnerUser(CreatePartnerUserDTO dto) {
		// 校验用户名唯一性
		LambdaQueryWrapper<BaseUser> usernameWrapper = Wrappers.lambdaQuery();
		usernameWrapper.eq(BaseUser::getUsername, dto.getUsername());
		if (baseUserMapper.selectCount(usernameWrapper) > 0) {
			throw new BusinessException("USERNAME_EXISTS", "用户名已存在");
		}

		// 校验手机号唯一性
		LambdaQueryWrapper<BaseUser> phoneWrapper = Wrappers.lambdaQuery();
		phoneWrapper.eq(BaseUser::getPhone, dto.getPhone());
		if (baseUserMapper.selectCount(phoneWrapper) > 0) {
			throw new BusinessException("PHONE_EXISTS", "手机号已存在");
		}

		// 密码加密处理
		String encodedPassword = passwordEncoder.encode(dto.getPassword());

		// 插入base_user表
		BaseUser baseUser = new BaseUser();
		baseUser.setUsername(dto.getUsername());
		baseUser.setPassword(encodedPassword);
		baseUser.setPhone(dto.getPhone());
		baseUser.setEmail(dto.getEmail());
		baseUser.setNickname(dto.getNickname());
		baseUser.setAvatar(dto.getAvatar());
		baseUser.setStatus(dto.getStatus() != null ? dto.getStatus() : 0); //合伙人创建时暂时不启用
		baseUser.setUserType(UserType.PARTNER.getCode());
		baseUserMapper.insert(baseUser);

		// 生成邀请码（使用用户ID+随机字符串）
		String inviteCode = "INV" + baseUser.getId() + generateRandomCode(4);

		// 生成二维码并上传到OSS
		String inviteCodePath = null;
		try {
			// 使用Hutool的QrCodeUtil生成二维码到临时文件
			File tempFile = File.createTempFile("qrcode_", ".png");
			tempFile.deleteOnExit();
			QrCodeUtil.generate(inviteCode, 300, 300, tempFile);

			// 上传到OSS（bucket: local, 路径: qrcode/邀请码.png）
			String objectName = "qrcode/" + inviteCode + ".png";
			InputStream inputStream = Files.newInputStream(tempFile.toPath());
			fileTemplate.putObject("local", objectName, inputStream, "image/png");

			inviteCodePath = objectName;
			log.info("二维码生成并上传成功，邀请码：{}，路径：{}", inviteCode, objectName);
		} catch (Exception e) {
			log.error("二维码生成或上传失败，邀请码：{}", inviteCode, e);
			// 二维码生成失败不影响用户创建，继续执行
		}

		// 插入user_partner表
		UserPartner userPartner = new UserPartner();
		userPartner.setBaseUserId(baseUser.getId());
		userPartner.setPartnerName(dto.getPartnerName());
		userPartner.setInviteCode(inviteCode);
		userPartner.setInviteCodePath(inviteCodePath);
		userPartner.setCardNumber(dto.getCardNumber());
		userPartner.setCommissionRate(dto.getCommissionRate());
		userPartner.setParentId(dto.getParentId() != null ? dto.getParentId() : 0L);
		userPartnerMapper.insert(userPartner);

		log.info("合伙人用户创建成功，用户ID：{}，用户名：{}，合伙人姓名：{}，邀请码：{}",
			baseUser.getId(), dto.getUsername(), dto.getPartnerName(), inviteCode);

		// 创建用户角色关联
		if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
			for (Long roleId : dto.getRoleIds()) {
				UserRole userRole = new UserRole();
				userRole.setUserId(baseUser.getId());
				userRole.setRoleId(roleId);
				userRoleMapper.insert(userRole);
			}
			log.info("合伙人用户角色关联创建成功，用户ID：{}，角色ID列表：{}", baseUser.getId(), dto.getRoleIds());
		}

		// 构建并返回UserPartnerListVO
		UserPartnerListVO vo = new UserPartnerListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(maskPhone(baseUser.getPhone()));
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setPartnerName(userPartner.getPartnerName());
		vo.setInviteCode(userPartner.getInviteCode());
		vo.setCommissionRate(userPartner.getCommissionRate());
		vo.setParentId(userPartner.getParentId());
		vo.setCreateTime(baseUser.getCreateAt());

		// 获取上级合伙人名称
		if (userPartner.getParentId() != null && userPartner.getParentId() > 0) {
			UserPartner parentPartner = userPartnerMapper.selectOne(
				Wrappers.lambdaQuery(UserPartner.class).eq(UserPartner::getBaseUserId, userPartner.getParentId())
			);
			if (parentPartner != null) {
				vo.setParentName(parentPartner.getPartnerName());
			}
		}

		return vo;
	}

	/**
	 * 生成随机邀请码
	 */
	private String generateRandomCode(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(random.nextInt(chars.length())));
		}
		return sb.toString();
	}

	private void validatePageParams(Integer page, Integer size) {
		if (page == null || page < 1) {
			throw new BusinessException("INVALID_PAGE", "页码必须大于0");
		}
		if (size == null || size < 1 || size > 1000) {
			throw new BusinessException("INVALID_SIZE", "每页数量必须在1-1000之间");
		}
	}

	private void validatePhone(String phone) {
		if (!phone.matches("^1[3-9]\\d{9}$")) {
			throw new BusinessException("INVALID_PHONE", "手机号格式不正确");
		}
	}

	private void validateEmail(String email) {
		if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			throw new BusinessException("INVALID_EMAIL", "邮箱格式不正确");
		}
	}

	private void validatePassword(String password) {
		if (password.length() < 8 || password.length() > 20) {
			throw new BusinessException("INVALID_PASSWORD_LENGTH", "密码长度必须在8-20位之间");
		}
	}

	@Override
	public UserAppListVO getAppUserDetail(Long id) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		if (!UserType.APP.getCode().equals(baseUser.getUserType())) {
			throw new BusinessException("INVALID_USER_TYPE", "用户类型不匹配");
		}

		UserAppListVO vo = new UserAppListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(baseUser.getPhone());
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setCreateTime(baseUser.getCreateAt());

		Map<Long, UserApp> userAppMap = batchQueryUserApp(Collections.singletonList(id));
		Map<Long, SysSchool> schoolMap = batchQuerySchools(userAppMap.values());
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(Collections.singletonList(id));

		fillAppUserDetail(vo, baseUser.getId(), userAppMap, schoolMap);
		fillRoleInfo(vo, baseUser.getId(), userRoleMap);

		return vo;
	}

	@Override
	public UserMchListVO getMchUserDetail(Long id) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		if (!UserType.MERCHANT.getCode().equals(baseUser.getUserType())) {
			throw new BusinessException("INVALID_USER_TYPE", "用户类型不匹配");
		}

		UserMchListVO vo = new UserMchListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(baseUser.getPhone());
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setCreateTime(baseUser.getCreateAt());

		Map<Long, UserMch> userMchMap = batchQueryUserMch(Collections.singletonList(id));
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(Collections.singletonList(id));

		fillMchUserDetail(vo, baseUser.getId(), userMchMap);
		fillRoleInfo(vo, baseUser.getId(), userRoleMap);

		return vo;
	}

	@Override
	public UserRiderListVO getRiderUserDetail(Long id) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		if (!UserType.RIDER.getCode().equals(baseUser.getUserType())) {
			throw new BusinessException("INVALID_USER_TYPE", "用户类型不匹配");
		}

		UserRiderListVO vo = new UserRiderListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(baseUser.getPhone());
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setCreateTime(baseUser.getCreateAt());

		Map<Long, UserRider> userRiderMap = batchQueryUserRider(Collections.singletonList(id));
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(Collections.singletonList(id));

		fillRiderUserDetail(vo, baseUser.getId(), userRiderMap);
		fillRoleInfo(vo, baseUser.getId(), userRoleMap);

		return vo;
	}

	@Override
	public UserSysListVO getSysUserDetail(Long id) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		if (!UserType.SYSTEM.getCode().equals(baseUser.getUserType())) {
			throw new BusinessException("INVALID_USER_TYPE", "用户类型不匹配");
		}

		UserSysListVO vo = new UserSysListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(baseUser.getPhone());
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setCreateTime(baseUser.getCreateAt());
		vo.setLastLoginAt(baseUser.getLastLoginAt());

		Map<Long, UserSys> userSysMap = batchQueryUserSys(Collections.singletonList(id));
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(Collections.singletonList(id));

		fillSysUserDetail(vo, baseUser.getId(), userSysMap);
		fillRoleInfo(vo, baseUser.getId(), userRoleMap);

		return vo;
	}

	@Override
	public UserPartnerListVO getPartnerUserDetail(Long id) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
		}

		BaseUser baseUser = baseUserMapper.selectById(id);
		if (baseUser == null) {
			throw new BusinessException("USER_NOT_FOUND", "用户不存在");
		}

		if (!UserType.PARTNER.getCode().equals(baseUser.getUserType())) {
			throw new BusinessException("INVALID_USER_TYPE", "用户类型不匹配");
		}

		UserPartnerListVO vo = new UserPartnerListVO();
		vo.setId(baseUser.getId());
		vo.setUsername(baseUser.getUsername());
		vo.setPhone(baseUser.getPhone());
		vo.setAvatar(baseUser.getAvatar());
		vo.setNickname(baseUser.getNickname());
		vo.setEmail(baseUser.getEmail());
		vo.setStatus(baseUser.getStatus());
		vo.setUserType(baseUser.getUserType());
		vo.setCreateTime(baseUser.getCreateAt());

		Map<Long, UserPartner> userPartnerMap = batchQueryUserPartner(Collections.singletonList(id));
		Map<Long, List<Role>> userRoleMap = batchQueryUserRoles(Collections.singletonList(id));

		fillPartnerUserDetail(vo, baseUser.getId(), userPartnerMap);

				fillRoleInfo(vo, baseUser.getId(), userRoleMap);

		

				return vo;

			}

		

			@Override

			public MchInfoDTO getMchInfoByBaseUserId(Long baseUserId) {

				if (baseUserId == null) {

					throw new BusinessException("INVALID_PARAM", "用户ID不能为空");

				}

		

				LambdaQueryWrapper<UserMch> wrapper = Wrappers.lambdaQuery();

				wrapper.eq(UserMch::getBaseUserId, baseUserId);

				UserMch userMch = userMchMapper.selectOne(wrapper);

		

				if (userMch == null) {

					return null;

				}

		

				MchInfoDTO dto = new MchInfoDTO();

				dto.setMchId(userMch.getId());

				dto.setMchName(userMch.getMchName());

				dto.setLogo(userMch.getLogo());

				dto.setIsOpen(userMch.getIsOpen());

				return dto;

			}



			@Override

			public List<MchInfoDTO> batchGetMchInfo(List<Long> baseUserIds) {

				if (baseUserIds == null || baseUserIds.isEmpty()) {

					return Collections.emptyList();

				}



				LambdaQueryWrapper<UserMch> wrapper = Wrappers.lambdaQuery();

				wrapper.in(UserMch::getBaseUserId, baseUserIds);

				List<UserMch> userMchList = userMchMapper.selectList(wrapper);



				return userMchList.stream().map(userMch -> {

					MchInfoDTO dto = new MchInfoDTO();

					dto.setMchId(userMch.getId());

					dto.setMchName(userMch.getMchName());

					dto.setLogo(userMch.getLogo());

					dto.setIsOpen(userMch.getIsOpen());

					return dto;

				}).collect(Collectors.toList());

			}

		

		}
