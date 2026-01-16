package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.entity.SysMenu;
import com.example.admin.biz.dto.SysMenuAddDTO;
import com.example.admin.biz.dto.SysMenuUpdateDTO;
import com.example.admin.biz.mapper.SysMenuMapper;
import com.example.admin.biz.service.SysMenuService;
import com.example.admin.biz.vo.SysMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	@Override
	public List<SysMenuVO> getMenuTree() {
		List<SysMenu> menuList = list();
		return buildMenuTree(menuList, 0L);
	}

	@Override
	public SysMenuVO getMenuDetail(Long id) {
		SysMenu menu = getById(id);
		if (menu == null) {
			throw new RuntimeException("菜单不存在");
		}
		return convertToVO(menu);
	}

	@Override
	public void addMenu(SysMenuAddDTO dto) {
		SysMenu menu = new SysMenu();
		menu.setParentId(dto.getParentId());
		menu.setMenuType(dto.getMenuType().toString());
		menu.setMenuIcon(dto.getMenuIcon());
		menu.setMenuName(dto.getMenuName());
		menu.setSortOrder(dto.getSortOrder());
		menu.setIsFrame(dto.getIsFrame() != null ? dto.getIsFrame().toString() : "1");
		menu.setPermission(dto.getPerms());
		menu.setComponent(dto.getComponent());
		menu.setPath(dto.getPath());
		menu.setStatus(dto.getStatus());
		save(menu);
	}

	@Override
	public void updateMenu(Long id, SysMenuUpdateDTO dto) {
		SysMenu menu = getById(id);
		if (menu == null) {
			throw new RuntimeException("菜单不存在");
		}
		menu.setParentId(dto.getParentId());
		menu.setMenuType(dto.getMenuType().toString());
		menu.setMenuIcon(dto.getMenuIcon());
		menu.setMenuName(dto.getMenuName());
		menu.setSortOrder(dto.getSortOrder());
		menu.setIsFrame(dto.getIsFrame() != null ? dto.getIsFrame().toString() : "1");
		menu.setPermission(dto.getPerms());
		menu.setComponent(dto.getComponent());
		menu.setPath(dto.getPath());
		menu.setStatus(dto.getStatus());
		updateById(menu);
	}

	@Override
	public void deleteMenu(Long id) {
		SysMenu menu = getById(id);
		if (menu == null) {
			throw new RuntimeException("菜单不存在");
		}
		LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(SysMenu::getParentId, id);
		long childCount = count(wrapper);
		if (childCount > 0) {
			throw new RuntimeException("存在子菜单，无法删除");
		}
		removeById(id);
	}

	private List<SysMenuVO> buildMenuTree(List<SysMenu> menuList, Long parentId) {
		List<SysMenuVO> tree = new ArrayList<>();
		for (SysMenu menu : menuList) {
			if (parentId.equals(menu.getParentId())) {
				SysMenuVO vo = convertToVO(menu);
				vo.setChildren(buildMenuTree(menuList, menu.getId()));
				tree.add(vo);
			}
		}
		return tree;
	}

	private SysMenuVO convertToVO(SysMenu menu) {
		SysMenuVO vo = new SysMenuVO();
		vo.setId(menu.getId());
		vo.setParentId(menu.getParentId());
		vo.setMenuType(Integer.parseInt(menu.getMenuType()));
		vo.setMenuIcon(menu.getMenuIcon());
		vo.setMenuName(menu.getMenuName());
		vo.setSortOrder(menu.getSortOrder());
		vo.setIsFrame(Integer.parseInt(menu.getIsFrame()));
		vo.setPerms(menu.getPermission());
		vo.setComponent(menu.getComponent());
		vo.setPath(menu.getPath());
		vo.setStatus(menu.getStatus());
		vo.setCreatedAt(menu.getCreateAt());
		return vo;
	}
}
