package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.entity.Role;
import com.example.admin.api.entity.RoleMenu;
import com.example.admin.api.entity.UserRole;
import com.example.admin.biz.dto.RoleAddDTO;
import com.example.admin.biz.dto.RoleQueryDTO;
import com.example.admin.biz.dto.RoleUpdateDTO;
import com.example.admin.biz.mapper.RoleMapper;
import com.example.admin.biz.mapper.RoleMenuMapper;
import com.example.admin.biz.mapper.UserRoleMapper;
import com.example.admin.biz.service.RoleService;
import com.example.admin.biz.vo.RoleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	private final RoleMenuMapper roleMenuMapper;
	private final UserRoleMapper userRoleMapper;

	@Override
	public Page<RoleVO> listRoles(RoleQueryDTO queryDTO) {
		log.info("查询角色列表，参数：{}", queryDTO);
		Page<RoleVO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
		Page<RoleVO> result = baseMapper.selectRolePageWithMenus(page, queryDTO);
		log.info("查询角色列表成功，共{}条记录", result.getTotal());
		return result;
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
}
