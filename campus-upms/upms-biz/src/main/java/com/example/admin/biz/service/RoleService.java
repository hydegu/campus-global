package com.example.admin.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.biz.dto.RoleAddDTO;
import com.example.admin.biz.dto.RoleQueryDTO;
import com.example.admin.biz.dto.RoleUpdateDTO;
import com.example.admin.biz.vo.RoleVO;

public interface RoleService {

	Page<RoleVO> listRoles(RoleQueryDTO queryDTO);

	void addRole(RoleAddDTO dto);

	void updateRole(Long id, RoleUpdateDTO dto);

	void deleteRole(Long id);
}
