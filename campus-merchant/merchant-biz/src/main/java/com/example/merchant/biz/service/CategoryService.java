package com.example.merchant.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.category.*;
import com.example.merchant.api.entity.MchCategory;
import com.example.merchant.api.vo.CategoryVO;

import java.util.List;

/**
 * 商品分类服务
 *
 * @author campus-merchant
 */
public interface CategoryService extends IService<MchCategory> {

    /**
     * 分页查询商品分类列表
     *
     * @param queryDTO 查询条件
     * @return 分类分页结果
     */
    PageResult<CategoryVO> listCategories(CategoryQueryDTO queryDTO);

    /**
     * 查询商品分类详情
     *
     * @param id 分类ID
     * @return 分类详情
     */
    CategoryVO getCategoryDetail(Long id);

    /**
     * 新增商品分类
     *
     * @param dto 新增DTO
     */
    void addCategory(CategoryAddDTO dto);

    /**
     * 更新商品分类
     *
     * @param id  分类ID
     * @param dto 更新DTO
     */
    void updateCategory(Long id, CategoryUpdateDTO dto);

    /**
     * 删除商品分类
     *
     * @param id 分类ID
     */
    void deleteCategory(Long id);

    /**
     * 批量新增商品分类
     *
     * @param dto 批量新增DTO
     */
    void batchAddCategories(CategoryBatchAddDTO dto);

    /**
     * 批量更新商品分类
     *
     * @param dto 批量更新DTO
     */
    void batchUpdateCategories(CategoryBatchUpdateDTO dto);

    /**
     * 获取完整分类树
     *
     * @param isGlobal 是否全局分类：0-全局分类，1-商家自定义分类
     * @param mchId    商家ID（isGlobal=1时必填）
     * @return 分类树
     */
    List<CategoryVO> getCategoryTree(Integer isGlobal, Long mchId);

    /**
     * 获取指定父级的子分类列表
     *
     * @param parentId 父级ID
     * @return 子分类列表
     */
    List<CategoryVO> getCategoryChildren(Long parentId);

    /**
     * 修改分类状态
     *
     * @param id        分类ID
     * @param statusDTO 状态变更DTO
     */
    void updateCategoryStatus(Long id, CategoryStatusDTO statusDTO);
}