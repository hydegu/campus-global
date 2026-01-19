package com.example.admin.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.dto.RoleAddDTO;
import com.example.admin.api.dto.RoleQueryDTO;
import com.example.admin.api.dto.RoleUpdateDTO;
import com.example.admin.api.vo.RoleVO;
import com.example.admin.api.vo.SysMenuVO;

import java.util.List;

public interface RoleService {

	Page<RoleVO> listRoles(RoleQueryDTO queryDTO);

	List<RoleVO> getCurrentUserRoles();

	void addRole(RoleAddDTO dto);

	void updateRole(Long id, RoleUpdateDTO dto);

	void deleteRole(Long id);

	List<SysMenuVO> getRoleMenus(Long id);

	void updateRoleMenus(Long id, List<Long> menuIds);
}
