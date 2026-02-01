package com.example.service.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.mybatis.utils.PageResult;
import com.example.service.api.dto.*;
import com.example.service.api.entity.ErrandCategory;
import com.example.service.api.vo.ErrandCategoryVO;

import java.util.List;

/**
 * 服务分类服务接口
 */
public interface ErrandCategoryService extends IService<ErrandCategory> {

    /**
     * 分页查询服务分类列表
     */
    PageResult<ErrandCategoryVO> listCategories(ErrandCategoryQueryDTO queryDTO);

    /**
     * 查询服务分类详情
     */
    ErrandCategoryVO getCategoryDetail(Long id);

    /**
     * 新增服务分类
     */
    void addCategory(ErrandCategoryAddDTO dto);

    /**
     * 更新服务分类
     */
    void updateCategory(Long id, ErrandCategoryUpdateDTO dto);

    /**
     * 删除服务分类
     */
    void deleteCategory(Long id);

    /**
     * 批量新增服务分类
     */
    void batchAddCategories(ErrandCategoryBatchAddDTO dto);

    /**
     * 获取完整服务分类树
     */
    List<ErrandCategoryVO> getCategoryTree();

    /**
     * 获取子分类列表
     */
    List<ErrandCategoryVO> getCategoryChildren(Long parentId);
}