package com.example.admin.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.*;
import com.example.admin.api.vo.*;

import java.util.List;

public interface UserService {

	UserInfo getUserInfo(String username);

	UserInfo getUserInfoById(Long id);

	Page<UserAppListVO> listAppUsers(UserQueryDTO queryDTO);

	Page<UserMchListVO> listMchUsers(UserQueryDTO queryDTO);

	Page<UserRiderListVO> listRiderUsers(UserQueryDTO queryDTO);

	Page<com.example.admin.api.vo.UserSysListVO> listSysUsers(UserQueryDTO queryDTO);

	Page<UserPartnerListVO> listPartnerUsers(UserQueryDTO queryDTO);

	void updateUserStatus(Long id, UserStatusDTO statusDTO);

	void updateUser(Long id, Integer userType, Object updateDTO);

	void updateSysUser(Long id, UpdateUserSysDTO dto);

	void updateAppUser(Long id, UpdateUserAppDTO dto);

	void updateMchUser(Long id, UpdateUserMchDTO dto);

	void updateRiderUser(Long id, UpdateUserRiderDTO dto);

	void updatePartnerUser(Long id, UpdateUserPartnerDTO dto);

	void resetUserPassword(Long id, ResetPasswordDTO resetPasswordDTO);

	void deleteUser(Long id);

	UserAppListVO createAppUser(CreateAppUserDTO dto);

	UserMchListVO createMchUser(CreateMchUserDTO dto);

	UserRiderListVO createRiderUser(CreateRiderUserDTO dto);

	UserSysListVO createSysUser(CreateSysUserDTO dto);

	UserPartnerListVO createPartnerUser(CreatePartnerUserDTO dto);

	/**
	 * 查询C端用户详情
	 * @param id 用户ID
	 * @return C端用户详情信息
	 */
	UserAppListVO getAppUserDetail(Long id);

	/**
	 * 查询商家用户详情
	 * @param id 用户ID
	 * @return 商家用户详情信息
	 */
	UserMchListVO getMchUserDetail(Long id);

	/**
	 * 查询骑手用户详情
	 * @param id 用户ID
	 * @return 骑手用户详情信息
	 */
	UserRiderListVO getRiderUserDetail(Long id);

	/**
	 * 查询系统用户详情
	 * @param id 用户ID
	 * @return 系统用户详情信息
	 */
	UserSysListVO getSysUserDetail(Long id);

	/**
	 * 查询合伙人用户详情
	 * @param id 用户ID
	 * @return 合伙人用户详情信息
	 */
	UserPartnerListVO getPartnerUserDetail(Long id);

	/**
	 * 根据商家用户ID获取商家信息
	 * @param baseUserId 商家用户ID
	 * @return 商家信息
	 */
	MchInfoDTO getMchInfoByBaseUserId(Long baseUserId);

	/**
	 * 批量获取商家信息
	 * @param baseUserIds 商家用户ID列表
	 * @return 商家信息列表
	 */
	List<MchInfoDTO> batchGetMchInfo(List<Long> baseUserIds);

	/**
	 * 更新用户余额或累计总收入（支持商家/骑手/合伙人）
	 * @param dto 用户余额更新DTO
	 */
	void updateUserBalance(MerchantBalanceUpdateDTO dto);
}
