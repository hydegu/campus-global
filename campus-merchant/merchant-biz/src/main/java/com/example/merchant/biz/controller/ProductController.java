package com.example.merchant.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.common.security.annotation.Inner;
import com.example.merchant.api.dto.product.*;
import com.example.merchant.api.vo.ProductVO;
import com.example.merchant.biz.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理
 *
 * @author campus-merchant
 */
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Validated
@Tag(name = "商品管理", description = "商品的增删改查功能")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    @Operation(summary = "分页查询商品列表",
            description = "根据查询条件分页查询商品列表。支持按商品名称、标题、分类ID、商家ID、价格区间、上架状态、规格类型等条件筛选。返回包含商品信息的分页结果。")
    public Result<PageResult<ProductVO>> listProducts(@ParameterObject ProductQueryDTO queryDTO) {
        PageResult<ProductVO> pageResult = productService.listProducts(queryDTO);
        return Result.ok(pageResult);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询商品详情",
            description = "通过商品ID查询商品详细信息，包括商品基本信息、关联分类列表、规格列表（specType=2时）、商家信息等。")
    public Result<ProductVO> getProductDetail(@PathVariable Long id) {
        ProductVO vo = productService.getProductDetail(id);
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "新增商品",
            description = "创建新的商品。必填字段：商品名称、商品标题、商品主图、分类ID列表、商家ID、规格类型。specType=2时必须提供specs列表。支持同时关联多个分类和创建多个规格。所有操作在一个事务中完成。")
    public Result<Void> addProduct(@Validated @RequestBody ProductAddDTO dto) {
        productService.addProduct(dto);
        return Result.ok();
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新商品",
            description = "更新指定的商品信息。支持更新商品基本信息、分类关联、规格信息。如果提供了categoryIds，将完全替换原有分类关联。如果提供了specs，将完全替换原有规格。所有操作在一个事务中完成。")
    public Result<Void> updateProduct(@PathVariable Long id, @Validated @RequestBody ProductUpdateDTO dto) {
        productService.updateProduct(id, dto);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除商品",
            description = "软删除指定的商品。将同时删除商品与分类的关联关系、商品与规格的关联关系。如果商品已上架，不允许删除。")
    public Result<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return Result.ok();
    }

    @PostMapping("/batch")
    @Operation(summary = "批量新增商品",
            description = "批量创建商品。每个商品可包含多个分类和规格。所有商品将作为一个事务执行，全部成功或全部失败。适用于需要批量导入商品的场景。")
    public Result<Void> batchAddProducts(@Validated @RequestBody ProductBatchAddDTO dto) {
        productService.batchAddProducts(dto);
        return Result.ok();
    }

    @Inner
    @PutMapping("/{id}/shelf-status")
    @Operation(summary = "更新商品上架状态", description = "内部调用接口，用于审核模块更新商品上架状态")
    public Result<Void> updateShelfStatus(@PathVariable Long id, @RequestParam Integer shelfStatus) {
        ProductShelfStatusDTO statusDTO = new ProductShelfStatusDTO();
        statusDTO.setShelfStatus(shelfStatus);
        productService.updateProductShelfStatus(id, statusDTO);
        return Result.ok();
    }

    @Inner
    @GetMapping("/by-audit-id")
    @Operation(summary = "根据审核记录ID查询商品", description = "内部调用接口")
    public Result<ProductVO> getByAuditId(@RequestParam Long auditId) {
        ProductVO vo = productService.getByAuditId(auditId);
        return Result.ok(vo);
    }
}