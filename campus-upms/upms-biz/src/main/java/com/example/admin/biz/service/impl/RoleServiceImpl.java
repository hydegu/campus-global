package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.entity.Role;
import com.example.admin.api.entity.RoleMenu;
import com.example.admin.api.entity.SysMenu;
import com.example.admin.api.entity.UserRole;
import com.example.admin.api.dto.RoleAddDTO;
import com.example.admin.api.dto.RoleQueryDTO;
import com.example.admin.api.dto.RoleUpdateDTO;
import com.example.admin.biz.mapper.RoleMapper;
import com.example.admin.biz.mapper.RoleMenuMapper;
import com.example.admin.biz.mapper.SysMenuMapper;
import com.example.admin.biz.mapper.UserRoleMapper;
import com.example.admin.biz.service.RoleService;
import com.example.admin.api.vo.RoleVO;
import com.example.admin.api.vo.SysMenuVO;
import com.example.common.core.exception.BusinessException;
import com.example.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	private final RoleMenuMapper roleMenuMapper;
	private final UserRoleMapper userRoleMapper;
	private final SysMenuMapper sysMenuMapper;

	@Override
	public Page<RoleVO> listRoles(RoleQueryDTO queryDTO) {
		log.info("查询角色列表，参数：{}", queryDTO);
		Page<RoleVO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
		Page<RoleVO> result = baseMapper.selectRolePageWithMenus(page, queryDTO);
		log.info("查询角色列表成功，共{}条记录", result.getTotal());
		return result;
	}

	@Override
	public List<RoleVO> getCurrentUserRoles() {
		log.info("查询当前用户角色列表");
		Long userId = SecurityUtils.getCurrentUserId();
		if (userId == 0) {
			throw new BusinessException("USER_NOT_AUTHENTICATED", "用户未登录");
		}

		LambdaQueryWrapper<UserRole> userRoleWrapper = new LambdaQueryWrapper<>();
		userRoleWrapper.eq(UserRole::getUserId, userId);
		List<UserRole> userRoles = userRoleMapper.selectList(userRoleWrapper);

		if (userRoles.isEmpty()) {
			log.info("当前用户没有分配角色，用户ID：{}", userId);
			return new ArrayList<>();
		}

		List<Long> roleIds = userRoles.stream()
			.map(UserRole::getRoleId)
			.collect(Collectors.toList());

		LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
		roleWrapper.in(Role::getId, roleIds);
		roleWrapper.orderByAsc(Role::getSortOrder);
		List<Role> roles = list(roleWrapper);

		List<RoleVO> roleVOList = roles.stream()
			.map(role -> {
				RoleVO vo = new RoleVO();
				vo.setId(role.getId());
				vo.setRoleName(role.getRoleName());
				vo.setRoleCode(role.getRoleCode());
				vo.setSortOrder(role.getSortOrder());
				vo.setStatus(role.getStatus());
				vo.setCreatedAt(role.getCreateAt());
				vo.setUpdatedAt(role.getUpdateAt());
				return vo;
			})
			.collect(Collectors.toList());

		log.info("查询当前用户角色列表成功，用户ID：{}，角色数：{}", userId, roleVOList.size());
		return roleVOList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addRole(RoleAddDTO dto) {
		log.info("新增角色，参数：{}", dto);
		LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(Role::getRoleCode, dto.getRoleCode());
		Role existingRole = getOne(wrapper);
		if (existingRole != null) {
			log.error("角色标识已存在：{}", dto.getRoleCode());
			throw new RuntimeException("角色标识已存在");
		}

		Role role = new Role();
		role.setRoleName(dto.getRoleName());
		role.setRoleCode(dto.getRoleCode());
		role.setSortOrder(dto.getSort());
		role.setStatus(dto.getStatus());
		save(role);
		log.info("角色创建成功，ID：{}", role.getId());

		if (dto.getMenuIds() != null && !dto.getMenuIds().isEmpty()) {
			List<RoleMenu> roleMenus = dto.getMenuIds().stream()
				.map(menuId -> {
					RoleMenu roleMenu = new RoleMenu();
					roleMenu.setRoleId(role.getId());
					roleMenu.setMenuId(menuId);
					return roleMenu;
				})
				.collect(Collectors.toList());
			roleMenus.forEach(roleMenuMapper::insert);
			log.info("角色菜单关联创建成功，关联菜单数：{}", roleMenus.size());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRole(Long id, RoleUpdateDTO dto) {
		log.info("更新角色，ID：{}，参数：{}", id, dto);
		Role role = getById(id);
		if (role == null) {
			log.error("角色不存在，ID：{}", id);
			throw new RuntimeException("角色不存在");
		}

		if (dto.getRoleCode() != null && !dto.getRoleCode().equals(role.getRoleCode())) {
			LambdaQueryWrapper<UserRole> userRoleCheckWrapper = new LambdaQueryWrapper<>();
			userRoleCheckWrapper.eq(UserRole::getRoleId, id);
			long userCount = userRoleMapper.selectCount(userRoleCheckWrapper);
			if (userCount > 0) {
				log.error("该角色正在被用户使用，无法修改角色标识，ID：{}", id);
				throw new RuntimeException("该角色正在被用户使用，无法修改角色标识");
			}

			LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
			wrapper.eq(Role::getRoleCode, dto.getRoleCode());
			wrapper.ne(Role::getId, id);
			Role existingRole = getOne(wrapper);
			if (existingRole != null) {
				log.error("角色标识已存在：{}", dto.getRoleCode());
				throw new RuntimeException("角色标识已存在");
			}
			role.setRoleCode(dto.getRoleCode());
		}

		if (dto.getRoleName() != null) {
			role.setRoleName(dto.getRoleName());
		}
		if (dto.getSort() != null) {
			role.setSortOrder(dto.getSort());
		}
		if (dto.getStatus() != null) {
			role.setStatus(dto.getStatus());
		}
		updateById(role);
		log.info("角色更新成功，ID：{}", id);

		if (dto.getMenuIds() != null) {
			LambdaQueryWrapper<RoleMenu> deleteWrapper = new LambdaQueryWrapper<>();
			deleteWrapper.eq(RoleMenu::getRoleId, id);
			roleMenuMapper.delete(deleteWrapper);

			if (!dto.getMenuIds().isEmpty()) {
				List<RoleMenu> roleMenus = dto.getMenuIds().stream()
					.map(menuId -> {
						RoleMenu roleMenu = new RoleMenu();
						roleMenu.setRoleId(id);
						roleMenu.setMenuId(menuId);
						return roleMenu;
					})
					.collect(Collectors.toList());
				roleMenus.forEach(roleMenuMapper::insert);
				log.info("角色菜单关联更新成功，关联菜单数：{}", roleMenus.size());
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteRole(Long id) {
		log.info("删除角色，ID：{}", id);
		Role role = getById(id);
		if (role == null) {
			log.error("角色不存在，ID：{}", id);
			throw new RuntimeException("角色不存在");
		}

		LambdaQueryWrapper<UserRole> userRoleCheckWrapper = new LambdaQueryWrapper<>();
		userRoleCheckWrapper.eq(UserRole::getRoleId, id);
		long userCount = userRoleMapper.selectCount(userRoleCheckWrapper);
		if (userCount > 0) {
			log.error("该角色正在被用户使用，无法删除，ID：{}，关联用户数：{}", id, userCount);
			throw new RuntimeException("该角色正在被用户使用，无法删除");
		}

		LambdaQueryWrapper<RoleMenu> roleMenuWrapper = new LambdaQueryWrapper<>();
		roleMenuWrapper.eq(RoleMenu::getRoleId, id);
		int deletedMenuCount = roleMenuMapper.delete(roleMenuWrapper);
		log.info("删除角色菜单关联，ID：{}，删除数量：{}", id, deletedMenuCount);

		removeById(id);
		log.info("角色删除成功，ID：{}", id);
	}

	@Override
	public List<SysMenuVO> getRoleMenus(Long id) {
		log.info("查询角色菜单，角色ID：{}", id);
		Role role = getById(id);
		if (role == null) {
			log.error("角色不存在，ID：{}", id);
			throw new RuntimeException("角色不存在");
		}

		LambdaQueryWrapper<RoleMenu> roleMenuWrapper = new LambdaQueryWrapper<>();
		roleMenuWrapper.eq(RoleMenu::getRoleId, id);
		List<RoleMenu> roleMenus = roleMenuMapper.selectList(roleMenuWrapper);

		if (roleMenus.isEmpty()) {
			log.info("角色没有关联菜单，角色ID：{}", id);
			return new ArrayList<>();
		}

		List<Long> menuIds = roleMenus.stream()
			.map(RoleMenu::getMenuId)
			.collect(Collectors.toList());

		LambdaQueryWrapper<SysMenu> menuWrapper = new LambdaQueryWrapper<>();
		menuWrapper.in(SysMenu::getId, menuIds);
		menuWrapper.orderByAsc(SysMenu::getSortOrder);
		List<SysMenu> menus = sysMenuMapper.selectList(menuWrapper);

		List<SysMenuVO> menuVOList = menus.stream()
			.map(menu -> {
				SysMenuVO vo = new SysMenuVO();
				BeanUtils.copyProperties(menu, vo);
				vo.setCreatedAt(menu.getCreateAt());
				return vo;
			})
			.collect(Collectors.toList());

		log.info("查询角色菜单成功，角色ID：{}，菜单数：{}", id, menuVOList.size());
		return menuVOList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRoleMenus(Long id, List<Long> menuIds) {
		log.info("更新角色菜单，角色ID：{}，菜单ID集合：{}", id, menuIds);
		Role role = getById(id);
		if (role == null) {
			log.error("角色不存在，ID：{}", id);
			throw new RuntimeException("角色不存在");
		}

		LambdaQueryWrapper<RoleMenu> deleteWrapper = new LambdaQueryWrapper<>();
		deleteWrapper.eq(RoleMenu::getRoleId, id);
		int deletedCount = roleMenuMapper.delete(deleteWrapper);
		log.info("删除原有角色菜单关联，角色ID：{}，删除数量：{}", id, deletedCount);

		if (menuIds != null && !menuIds.isEmpty()) {
			List<RoleMenu> roleMenus = menuIds.stream()
				.map(menuId -> {
					RoleMenu roleMenu = new RoleMenu();
					roleMenu.setRoleId(id);
					roleMenu.setMenuId(menuId);
					return roleMenu;
				})
				.collect(Collectors.toList());
			roleMenus.forEach(roleMenuMapper::insert);
			log.info("角色菜单关联更新成功，角色ID：{}，关联菜单数：{}", id, roleMenus.size());
		}

		log.info("更新角色菜单成功，角色ID：{}", id);
	}
}
