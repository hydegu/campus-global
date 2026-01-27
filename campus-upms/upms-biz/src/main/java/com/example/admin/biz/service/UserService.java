package com.example.admin.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.*;
import com.example.admin.api.vo.*;

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

	void resetUserPassword(Long id, ResetPasswordDTO resetPasswordDTO);

	void deleteUser(Long id);

	UserAppListVO createAppUser(CreateAppUserDTO dto);

	UserMchListVO createMchUser(CreateMchUserDTO dto);

	UserRiderListVO createRiderUser(CreateRiderUserDTO dto);

	UserSysListVO createSysUser(CreateSysUserDTO dto);

	UserPartnerListVO createPartnerUser(CreatePartnerUserDTO dto);
}
