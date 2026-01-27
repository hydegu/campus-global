package com.example.merchant.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.category.*;
import com.example.merchant.api.vo.CategoryVO;
import com.example.merchant.biz.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类管理
 *
 * @author campus-merchant
 */
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Validated
@Tag(name = "商品分类管理", description = "商品分类的增删改查功能")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    @Operation(summary = "分页查询商品分类列表",
            description = "根据查询条件分页查询商品分类列表，支持按分类名称、父级ID、分类层级、状态等条件筛选。返回包含分类信息的分页结果。")
    public Result<PageResult<CategoryVO>> listCategories(@ParameterObject CategoryQueryDTO queryDTO) {
        PageResult<CategoryVO> pageResult = categoryService.listCategories(queryDTO);
        return Result.ok(pageResult);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询商品分类详情",
            description = "通过分类ID查询商品分类详细信息，包括分类名称、父级分类、层级、排序、状态等信息。")
    public Result<CategoryVO> getCategoryDetail(@PathVariable Long id) {
        CategoryVO vo = categoryService.getCategoryDetail(id);
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "新增商品分类",
            description = "创建新的商品分类。必填字段：分类名称、父级ID、分类层级。支持创建多级分类（最多3级）。isGlobal=1时需要填写商家ID。")
    public Result<Void> addCategory(@Validated @RequestBody CategoryAddDTO dto) {
        categoryService.addCategory(dto);
        return Result.ok();
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新商品分类",
            description = "更新指定的商品分类信息。可更新字段：分类名称、父级ID、分类层级、排序、状态。不能修改isGlobal和mchId。")
    public Result<Void> updateCategory(@PathVariable Long id, @Validated @RequestBody CategoryUpdateDTO dto) {
        categoryService.updateCategory(id, dto);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除商品分类",
            description = "软删除指定的商品分类。如果分类下有子分类或关联商品，则不允许删除。")
    public Result<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return Result.ok();
    }

    @PostMapping("/batch")
    @Operation(summary = "批量新增商品分类",
            description = "批量创建商品分类。所有分类将作为一个事务执行，全部成功或全部失败。适用于需要批量导入分类的场景。")
    public Result<Void> batchAddCategories(@Validated @RequestBody CategoryBatchAddDTO dto) {
        categoryService.batchAddCategories(dto);
        return Result.ok();
    }

    @PutMapping("/batch")
    @Operation(summary = "批量更新商品分类",
            description = "批量更新商品分类信息。所有更新将作为一个事务执行，全部成功或全部失败。")
    public Result<Void> batchUpdateCategories(@Validated @RequestBody CategoryBatchUpdateDTO dto) {
        categoryService.batchUpdateCategories(dto);
        return Result.ok();
    }

    @GetMapping("/tree")
    @Operation(summary = "获取完整分类树",
            description = "返回所有层级的完整分类树形结构，包含所有子分类。支持按isGlobal和mchId筛选全局分类或商家自定义分类。")
    @Parameters({
            @Parameter(name = "isGlobal", description = "是否全局分类：0-全局分类，1-商家自定义分类"),
            @Parameter(name = "mchId", description = "商家ID（isGlobal=1时必填）")
    })
    public Result<List<CategoryVO>> getCategoryTree(@RequestParam(required = false) Integer isGlobal,
                                                     @RequestParam(required = false) Long mchId) {
        List<CategoryVO> tree = categoryService.getCategoryTree(isGlobal, mchId);
        return Result.ok(tree);
    }

    @GetMapping("/children/{parentId}")
    @Operation(summary = "获取指定父级的子分类列表",
            description = "查询指定父级分类下的直接子分类列表（仅一级，不包含更深层级）。parentId=0时返回顶级分类。")
    public Result<List<CategoryVO>> getCategoryChildren(@PathVariable Long parentId) {
        List<CategoryVO> children = categoryService.getCategoryChildren(parentId);
        return Result.ok(children);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "修改分类状态",
            description = "启用或禁用指定的商品分类。status=1启用，status=0禁用。禁用后，该分类下的商品将无法展示。")
    public Result<Void> updateCategoryStatus(@PathVariable Long id,
                                              @Validated @RequestBody CategoryStatusDTO statusDTO) {
        categoryService.updateCategoryStatus(id, statusDTO);
        return Result.ok();
    }
}