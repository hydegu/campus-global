package com.example.admin.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.ResetPasswordDTO;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.api.dto.UserQueryDTO;
import com.example.admin.api.vo.UserAppListVO;
import com.example.admin.api.vo.UserMchListVO;
import com.example.admin.api.vo.UserPartnerListVO;
import com.example.admin.api.vo.UserRiderListVO;

public interface UserService {

	UserInfo getUserInfo(String username);

	UserInfo getUserInfoById(Long id);

	Page<UserAppListVO> listAppUsers(UserQueryDTO queryDTO);

	Page<UserMchListVO> listMchUsers(UserQueryDTO queryDTO);

	Page<UserRiderListVO> listRiderUsers(UserQueryDTO queryDTO);

	Page<com.example.admin.api.vo.UserSysListVO> listSysUsers(UserQueryDTO queryDTO);

	Page<UserPartnerListVO> listPartnerUsers(UserQueryDTO queryDTO);

	void updateUserStatus(Long id, com.example.admin.api.dto.UserStatusDTO statusDTO);

	void updateUser(Long id, Integer userType, Object updateDTO);

	void resetUserPassword(Long id, ResetPasswordDTO resetPasswordDTO);

	void deleteUser(Long id);
}
