package com.example.admin.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.biz.dto.RoleAddDTO;
import com.example.admin.biz.dto.RoleQueryDTO;
import com.example.admin.biz.dto.RoleUpdateDTO;
import com.example.admin.biz.service.RoleService;
import com.example.admin.biz.vo.RoleVO;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import com.example.common.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys/role")
@RequiredArgsConstructor
@StandardApiResponses
@Tag(name = "角色管理", description = "系统角色的增删改查功能")
public class RoleController {

	private final RoleService roleService;

	@GetMapping("/list")
	@Operation(summary = "分页查询系统角色列表", description = "根据查询条件分页查询系统角色列表，支持按角色名称、权限字符、角色状态等条件筛选。返回包含角色信息的分页结果。需要权限：system:role:list")
	@HasPermission("system:role:list")
	public Result<Page<RoleVO>> listRoles(@Valid RoleQueryDTO queryDTO) {
		Page<RoleVO> page = roleService.listRoles(queryDTO);
		return Result.ok(page);
	}

	@PostMapping
	@Operation(summary = "新增角色", description = "创建新的系统角色。必填字段：角色名称、角色标识、菜单ID集合、排序、状态。需要权限：system:role:add")
	@HasPermission("system:role:add")
	public Result<Void> addRole(@Valid @RequestBody RoleAddDTO dto) {
		roleService.addRole(dto);
		return Result.ok();
	}

	@PutMapping("/{id}")
	@Operation(summary = "修改角色", description = "更新系统角色信息，所有字段均可选。需要权限：system:role:edit")
	@HasPermission("system:role:edit")
	public Result<Void> updateRole(@PathVariable Long id, @Valid @RequestBody RoleUpdateDTO dto) {
		roleService.updateRole(id, dto);
		return Result.ok();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除角色", description = "删除指定的系统角色。需要权限：system:role:delete")
	@HasPermission("system:role:delete")
	public Result<Void> deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return Result.ok();
	}
}
