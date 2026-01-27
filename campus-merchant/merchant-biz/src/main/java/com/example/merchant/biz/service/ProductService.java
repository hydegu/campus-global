package com.example.merchant.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.product.*;
import com.example.merchant.api.entity.MchProduct;
import com.example.merchant.api.vo.ProductVO;

/**
 * 商品服务
 *
 * @author campus-merchant
 */
public interface ProductService extends IService<MchProduct> {

    /**
     * 分页查询商品列表
     *
     * @param queryDTO 查询条件
     * @return 商品分页结果
     */
    PageResult<ProductVO> listProducts(ProductQueryDTO queryDTO);

    /**
     * 查询商品详情
     *
     * @param id 商品ID
     * @return 商品详情
     */
    ProductVO getProductDetail(Long id);

    /**
     * 新增商品
     *
     * @param dto 新增DTO（同时操作规格和分类）
     */
    void addProduct(ProductAddDTO dto);

    /**
     * 更新商品
     *
     * @param id  商品ID
     * @param dto 更新DTO（同时操作规格和分类）
     */
    void updateProduct(Long id, ProductUpdateDTO dto);

    /**
     * 删除商品
     *
     * @param id 商品ID
     */
    void deleteProduct(Long id);

    /**
     * 批量新增商品
     *
     * @param dto 批量新增DTO
     */
    void batchAddProducts(ProductBatchAddDTO dto);

    /**
     * 批量更新商品
     *
     * @param dto 批量更新DTO
     */
    void batchUpdateProducts(ProductBatchUpdateDTO dto);

    /**
     * 修改商品上架状态
     *
     * @param id        商品ID
     * @param statusDTO 状态变更DTO
     */
    void updateProductShelfStatus(Long id, ProductShelfStatusDTO statusDTO);
}