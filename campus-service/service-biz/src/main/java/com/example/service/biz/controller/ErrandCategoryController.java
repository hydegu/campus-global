package com.example.service.biz.controller;

import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.service.api.dto.*;
import com.example.service.api.vo.ErrandCategoryVO;
import com.example.service.biz.service.ErrandCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 服务分类管理Controller
 */
@RestController
@RequestMapping("/api/errand-category")
@RequiredArgsConstructor
@Validated
@Tag(name = "服务分类管理", description = "服务分类的增删改查功能")
public class ErrandCategoryController {

    private final ErrandCategoryService errandCategoryService;

    @GetMapping("/list")
    @Operation(summary = "分页查询服务分类列表", description = "根据查询条件分页查询服务分类列表，支持按分类名称、父级ID、分类层级、状态等条件筛选。返回包含分类信息的分页结果。")
    public Result<PageResult<ErrandCategoryVO>> listCategories(@Valid ErrandCategoryQueryDTO queryDTO) {
        PageResult<ErrandCategoryVO> page = errandCategoryService.listCategories(queryDTO);
        return Result.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询服务分类详情", description = "通过分类ID查询服务分类详细信息，包括分类名称、父级分类、层级、排序、状态、分佣配置等信息。")
    public Result<ErrandCategoryVO> getCategoryDetail(@PathVariable Long id) {
        ErrandCategoryVO vo = errandCategoryService.getCategoryDetail(id);
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "新增服务分类", description = "创建新的服务分类。必填字段：父级ID、分类层级、分类名称。支持创建多级分类（最多2级）。")
    public Result<Void> addCategory(@Valid @RequestBody ErrandCategoryAddDTO dto) {
        errandCategoryService.addCategory(dto);
        return Result.ok();
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新服务分类", description = "更新指定的服务分类信息。可更新字段：分类名称、排序、是否允许线下交易、状态、分佣比例。")
    public Result<Void> updateCategory(@PathVariable Long id, @Valid @RequestBody ErrandCategoryUpdateDTO dto) {
        errandCategoryService.updateCategory(id, dto);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除服务分类", description = "软删除指定的服务分类。如果分类下有子分类或关联服务，则不允许删除。需要权限：service:category:delete")
    public Result<Void> deleteCategory(@PathVariable Long id) {
        errandCategoryService.deleteCategory(id);
        return Result.ok();
    }

    @PostMapping("/batch")
    @Operation(summary = "批量新增服务分类", description = "批量创建服务分类。所有分类将作为一个事务执行，全部成功或全部失败。适用于需要批量导入分类的场景。")
    public Result<Void> batchAddCategories(@Valid @RequestBody ErrandCategoryBatchAddDTO dto) {
        errandCategoryService.batchAddCategories(dto);
        return Result.ok();
    }

    @PutMapping("/batch")
    @Operation(summary = "批量更新服务分类", description = "批量更新服务分类信息。所有更新将作为一个事务执行，全部成功或全部失败。")
    public Result<Void> batchUpdateCategories(@Valid @RequestBody ErrandCategoryBatchUpdateDTO dto) {
        errandCategoryService.batchUpdateCategories(dto);
        return Result.ok();
    }

    @GetMapping("/tree")
    @Operation(summary = "获取完整服务分类树", description = "返回所有层级的完整服务分类树形结构，包含所有子分类。支持按状态筛选。")
    public Result<List<ErrandCategoryVO>> getCategoryTree() {
        List<ErrandCategoryVO> tree = errandCategoryService.getCategoryTree();
        return Result.ok(tree);
    }

    @GetMapping("/children/{parentId}")
    @Operation(summary = "获取子分类列表", description = "查询指定父级分类下的直接子分类列表（仅一级，不包含更深层级）。parentId=0时返回顶级分类。")
    public Result<List<ErrandCategoryVO>> getCategoryChildren(@PathVariable Long parentId) {
        List<ErrandCategoryVO> children = errandCategoryService.getCategoryChildren(parentId);
        return Result.ok(children);
    }
}