package com.example.admin.biz.controller;

import com.example.admin.api.dto.SysMenuAddDTO;
import com.example.admin.api.dto.SysMenuUpdateDTO;
import com.example.admin.biz.service.SysMenuService;
import com.example.admin.api.vo.SysMenuVO;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "菜单管理", description = "系统菜单的增删改查功能")
public class SysMenuController {

	private final SysMenuService sysMenuService;

	@GetMapping("/tree")
	@Operation(summary = "获取菜单树", description = "查询所有系统菜单，返回树形结构的菜单列表。需要权限：system:menu:list")
	public Result<List<SysMenuVO>> getMenuTree() {
		List<SysMenuVO> menuTree = sysMenuService.getMenuTree();
		return Result.ok(menuTree);
	}

	@GetMapping("/current-user/tree")
	@Operation(summary = "获取当前用户菜单树", description = "查询当前登录用户的菜单权限，返回树形结构的菜单列表")
	public Result<List<SysMenuVO>> getCurrentUserMenuTree() {
		List<SysMenuVO> menuTree = sysMenuService.getCurrentUserMenuTree();
		return Result.ok(menuTree);
	}

	@GetMapping("/{id}")
	@Operation(summary = "获取菜单详情", description = "根据菜单ID查询菜单详细信息。需要权限：system:menu:query")
	public Result<SysMenuVO> getMenuDetail(@PathVariable Long id) {
		SysMenuVO menuDetail = sysMenuService.getMenuDetail(id);
		return Result.ok(menuDetail);
	}

	@PostMapping
	@Operation(summary = "新增菜单", description = "创建新的系统菜单。必填字段：菜单名称、菜单类型、路径等。需要权限：system:menu:add",
		responses = {
			@ApiResponse(responseCode = "409", description = "菜单ID已存在",
				content = @Content(
					mediaType = "application/json",
					examples = @ExampleObject(
						value = """
							{
								"code": 409,
								"message": "已存在此id的菜单：用户管理",
								"result": null
							}
						"""
					)
				)
			)
		}
	)
	public Result<Void> addMenu(@Valid @RequestBody SysMenuAddDTO dto) {
		sysMenuService.addMenu(dto);
		return Result.ok();
	}

	@PutMapping("/{id}")
	@Operation(summary = "更新菜单", description = "更新系统菜单信息，所有字段均可选。需要权限：system:menu:edit")
	public Result<Void> updateMenu(@PathVariable Long id, @Valid @RequestBody SysMenuUpdateDTO dto) {
		sysMenuService.updateMenu(id, dto);
		return Result.ok();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除菜单", description = "删除指定的系统菜单")
	public Result<Void> deleteMenu(@PathVariable Long id) {
		sysMenuService.deleteMenu(id);
		return Result.ok();
	}
}
