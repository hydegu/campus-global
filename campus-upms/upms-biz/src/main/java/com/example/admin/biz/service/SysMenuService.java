package com.example.admin.biz.service;

import com.example.admin.biz.dto.SysMenuAddDTO;
import com.example.admin.biz.dto.SysMenuUpdateDTO;
import com.example.admin.biz.vo.SysMenuVO;

import java.util.List;

public interface SysMenuService {

	List<SysMenuVO> getMenuTree();

	List<SysMenuVO> getCurrentUserMenuTree();

	SysMenuVO getMenuDetail(Long id);

	void addMenu(SysMenuAddDTO dto);

	void updateMenu(Long id, SysMenuUpdateDTO dto);

	void deleteMenu(Long id);
}
