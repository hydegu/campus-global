package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.entity.RoleMenu;
import com.example.admin.api.entity.SysMenu;
import com.example.admin.api.entity.UserRole;
import com.example.admin.biz.dto.SysMenuAddDTO;
import com.example.admin.biz.dto.SysMenuUpdateDTO;
import com.example.admin.biz.mapper.RoleMenuMapper;
import com.example.admin.biz.mapper.SysMenuMapper;
import com.example.admin.biz.mapper.UserRoleMapper;
import com.example.admin.biz.service.SysMenuService;
import com.example.admin.biz.vo.SysMenuVO;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.exception.CheckedException;
import com.example.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	private final UserRoleMapper userRoleMapper;
	private final RoleMenuMapper roleMenuMapper;

	@Override
	public List<SysMenuVO> getMenuTree() {
		List<SysMenu> menuList = list();
		return buildMenuTree(menuList, 0L);
	}

	@Override
	public List<SysMenuVO> getCurrentUserMenuTree() {
		Long userId = SecurityUtils.getCurrentUserId();
		if (userId == 0) {
			throw new BusinessException("USER_NOT_AUTHENTICATED", "用户未登录");
		}

		LambdaQueryWrapper<UserRole> userRoleWrapper = new LambdaQueryWrapper<>();
		userRoleWrapper.eq(UserRole::getUserId, userId);
		List<UserRole> userRoles = userRoleMapper.selectList(userRoleWrapper);

		if (userRoles.isEmpty()) {
			return new ArrayList<>();
		}

		List<Long> roleIds = userRoles.stream()
			.map(UserRole::getRoleId)
			.collect(Collectors.toList());

		LambdaQueryWrapper<RoleMenu> roleMenuWrapper = new LambdaQueryWrapper<>();
		roleMenuWrapper.in(RoleMenu::getRoleId, roleIds);
		List<RoleMenu> roleMenus = roleMenuMapper.selectList(roleMenuWrapper);

		if (roleMenus.isEmpty()) {
			return new ArrayList<>();
		}

		List<Long> menuIds = roleMenus.stream()
			.map(RoleMenu::getMenuId)
			.distinct()
			.collect(Collectors.toList());

		LambdaQueryWrapper<SysMenu> menuWrapper = new LambdaQueryWrapper<>();
		menuWrapper.in(SysMenu::getId, menuIds);
		List<SysMenu> userMenus = list(menuWrapper);

		return buildMenuTree(userMenus, 0L);
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
		if (dto.getId() != null) {
			SysMenu existingMenu = getById(dto.getId());
			if (existingMenu != null) {
				throw new BusinessException("MENU_ID_EXISTS", "已存在此id的菜单：" + existingMenu.getMenuName());
			}
			menu.setId(dto.getId());
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
		save(menu);
	}

	@Override
	public void updateMenu(Long id, SysMenuUpdateDTO dto) {
		if (dto.getId() != null && !dto.getId().equals(id)) {
			SysMenu existingMenu = getById(dto.getId());
			if (existingMenu != null) {
				throw new CheckedException("已存在此id的菜单：" + existingMenu.getMenuName());
			}
		}
		SysMenu menu = getById(id);
		if (menu == null) {
			throw new RuntimeException("菜单不存在");
		}
		if (dto.getParentId() != null) {
			menu.setParentId(dto.getParentId());
		}
		if (dto.getMenuType() != null) {
			menu.setMenuType(dto.getMenuType().toString());
		}
		if (dto.getMenuIcon() != null) {
			menu.setMenuIcon(dto.getMenuIcon());
		}
		if (dto.getMenuName() != null) {
			menu.setMenuName(dto.getMenuName());
		}
		if (dto.getSortOrder() != null) {
			menu.setSortOrder(dto.getSortOrder());
		}
		if (dto.getIsFrame() != null) {
			menu.setIsFrame(dto.getIsFrame().toString());
		}
		if (dto.getPerms() != null) {
			menu.setPermission(dto.getPerms());
		}
		if (dto.getComponent() != null) {
			menu.setComponent(dto.getComponent());
		}
		if (dto.getPath() != null) {
			menu.setPath(dto.getPath());
		}
		if (dto.getStatus() != null) {
			menu.setStatus(dto.getStatus());
		}
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
