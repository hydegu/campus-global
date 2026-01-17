package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.api.entity.*;
import com.example.admin.biz.dto.UserQueryDTO;
import com.example.admin.biz.dto.UserStatusDTO;
import com.example.admin.biz.dto.*;
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
import com.example.admin.biz.vo.UserAppListVO;
import com.example.admin.biz.vo.UserMchListVO;
import com.example.admin.biz.vo.UserPartnerListVO;
import com.example.admin.biz.vo.UserRiderListVO;
import com.example.admin.biz.vo.UserSysListVO;
import com.example.admin.biz.vo.*;
import com.example.common.core.enums.AuditStatus;
import com.example.common.core.enums.Gender;
import com.example.common.core.enums.UserType;
import com.example.common.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
			vo.setMchLogo(userMch.getLogo());
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
}
