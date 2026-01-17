package com.example.admin.biz.controller;

import com.example.admin.biz.dto.SysDictAddDTO;
import com.example.admin.biz.dto.SysDictPartialUpdateDTO;
import com.example.admin.biz.service.SysDictService;
import com.example.admin.biz.vo.SysDictVO;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dict")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "字典管理", description = "系统字典的增删改查功能")
public class SysDictController {

	private final SysDictService sysDictService;

	@PostMapping
	@Operation(summary = "新增字典", description = "创建新的系统字典，需要提供字典名称、编码、值等基本信息。字典编码必须唯一。需要权限：system:dict:add")
	public Result<Void> addDict(@Valid @RequestBody SysDictAddDTO dto) {
		sysDictService.addDict(dto);
		return Result.ok();
	}

	@PutMapping("/{id}")
	@Operation(summary = "部分更新字典", description = "部分更新字典信息，只更新传入的字段。可以更新字典名称、值、排序、状态等信息。需要权限：system:dict:edit")
	public Result<Void> updateDict(@PathVariable Long id, @Valid @RequestBody SysDictPartialUpdateDTO dto) {
		sysDictService.updateDict(id, dto);
		return Result.ok();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除字典", description = "删除指定的系统字典。删除前会检查字典是否被引用，如果有引用则不允许删除。需要权限：system:dict:delete")
	public Result<Void> deleteDict(@PathVariable Long id) {
		sysDictService.deleteDict(id);
		return Result.ok();
	}

	@GetMapping("/group/{id}")
	@Operation(summary = "查询字典树形结构", description = "根据父ID查询字典及其子字典树形结构，返回包含父字典详情及其子字典的树形数据。需要权限：system:dict:query")
	public Result<List<SysDictVO>> getDictTree(@PathVariable Long id) {
		List<SysDictVO> dictTree = sysDictService.getDictTree(id);
		return Result.ok(dictTree);
	}
}
