package com.example.admin.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.biz.dto.*;
import com.example.admin.biz.vo.*;

public interface UserService {

	UserInfo getUserInfo(String username);

	Page<UserAppListVO> listAppUsers(UserQueryDTO queryDTO);

	Page<UserMchListVO> listMchUsers(UserQueryDTO queryDTO);

	Page<UserRiderListVO> listRiderUsers(UserQueryDTO queryDTO);

	Page<UserSysListVO> listSysUsers(UserQueryDTO queryDTO);

	Page<UserPartnerListVO> listPartnerUsers(UserQueryDTO queryDTO);

	void updateUserStatus(Long id, UserStatusDTO statusDTO);

	void updateUser(Long id, Integer userType, Object updateDTO);

	void resetUserPassword(Long id, ResetPasswordDTO resetPasswordDTO);

	void deleteUser(Long id);
}
