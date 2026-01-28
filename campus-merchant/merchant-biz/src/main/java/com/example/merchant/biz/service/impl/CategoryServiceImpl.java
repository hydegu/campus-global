package com.example.merchant.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.BusinessException;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.category.*;
import com.example.merchant.api.entity.MchCategory;
import com.example.merchant.api.entity.MchProductCategory;
import com.example.merchant.api.vo.CategoryVO;
import com.example.merchant.biz.mapper.CategoryMapper;
import com.example.merchant.biz.mapper.ProductCategoryMapper;
import com.example.merchant.biz.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品分类服务实现
 *
 * @author campus-merchant
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, MchCategory>
        implements CategoryService {

    private final ProductCategoryMapper productCategoryMapper;

    @Override
    public PageResult<CategoryVO> listCategories(CategoryQueryDTO queryDTO) {
        log.info("分页查询商品分类列表，参数：{}", queryDTO);

        // 参数校验
        validatePageParams(queryDTO.getPageNum(), queryDTO.getPageSize());

        // 构建查询条件
        LambdaQueryWrapper<MchCategory> wrapper = buildCategoryWrapper(queryDTO);

        // 执行分页查询
        IPage<MchCategory> page = baseMapper.selectPage(
                new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()),
                wrapper
        );

        // 如果没有数据，返回空分页
        if (page.getRecords().isEmpty()) {
            return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), Collections.emptyList());
        }

        // 批量查询父分类信息
        Set<Long> parentIds = page.getRecords().stream()
                .map(MchCategory::getParentId)
                .filter(parentId -> parentId != null && parentId > 0)
                .collect(Collectors.toSet());
        Map<Long, MchCategory> parentMap;
        if (!parentIds.isEmpty()) {
            parentMap = baseMapper.selectBatchIds(parentIds).stream()
                    .collect(Collectors.toMap(MchCategory::getId, c -> c));
        } else {
            parentMap = Collections.emptyMap();
        }

        // 组装VO对象
        List<CategoryVO> voList = page.getRecords().stream()
                .map(category -> buildCategoryVO(category, parentMap))
                .collect(Collectors.toList());

        // 返回分页结果
        return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), voList);
    }

    @Override
    public CategoryVO getCategoryDetail(Long id) {
        log.info("查询商品分类详情，参数：id={}", id);

        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "分类ID不能为空");
        }

        // 查询分类信息
        MchCategory category = baseMapper.selectById(id);
        if (category == null) {
            throw new BusinessException("CATEGORY_NOT_FOUND", "分类不存在");
        }

        // 检查是否已删除
        if (category.getDeleteAt() != null) {
            throw new BusinessException("CATEGORY_DELETED", "分类已被删除");
        }

        // 查询父分类信息
        Map<Long, MchCategory> parentMap;
        if (category.getParentId() != null && category.getParentId() > 0) {
            MchCategory parent = baseMapper.selectById(category.getParentId());
            if (parent != null) {
                parentMap = Collections.singletonMap(parent.getId(), parent);
            } else {
                parentMap = Collections.emptyMap();
            }
        } else {
            parentMap = Collections.emptyMap();
        }

        // 构建VO对象
        return buildCategoryVO(category, parentMap);
    }

    @Override
    public void addCategory(CategoryAddDTO dto) {
        log.info("新增商品分类，参数：{}", dto);

        // 参数校验
        if (dto == null) {
            throw new BusinessException("INVALID_PARAM", "新增分类参数不能为空");
        }

        // 验证 isGlobal 和 mchId
        if (dto.getIsGlobal() != null && dto.getIsGlobal() == 1 && dto.getMchId() == null) {
            throw new BusinessException("INVALID_PARAM", "商家自定义分类必须提供商家ID");
        }

        // 如果提供了父分类ID，验证父分类是否存在
        if (dto.getParentId() != null && dto.getParentId() > 0) {
            MchCategory parent = baseMapper.selectById(dto.getParentId());
            if (parent == null) {
                throw new BusinessException("PARENT_CATEGORY_NOT_FOUND", "父分类不存在");
            }
            if (parent.getDeleteAt() != null) {
                throw new BusinessException("PARENT_CATEGORY_DELETED", "父分类已被删除");
            }

            // 计算层级并验证
            if (dto.getLevel() == null) {
                dto.setLevel(parent.getLevel() + 1);
            }
            if (dto.getLevel() > 3) {
                throw new BusinessException("CATEGORY_LEVEL_EXCEEDED", "分类层级不能超过3级");
            }
        } else {
            // 顶级分类
            if (dto.getLevel() == null) {
                dto.setLevel(1);
            }
        }

        // 构建分类实体
        MchCategory category = new MchCategory();
        category.setCategoryName(dto.getCategoryName());
        category.setParentId(dto.getParentId() != null ? dto.getParentId() : 0L);
        category.setLevel(dto.getLevel());
        category.setSortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 0);
        category.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);
        category.setIsGlobal(dto.getIsGlobal() != null ? dto.getIsGlobal() : 0);
        category.setMchId(dto.getMchId());
        category.setCreateAt(LocalDateTime.now());
        category.setUpdateAt(LocalDateTime.now());

        // 保存分类
        baseMapper.insert(category);

        log.info("新增商品分类成功，分类ID：{}", category.getId());
    }

    @Override
    public void updateCategory(Long id, CategoryUpdateDTO dto) {
        log.info("更新商品分类，参数：id={}, dto={}", id, dto);

        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "分类ID不能为空");
        }
        if (dto == null) {
            throw new BusinessException("INVALID_PARAM", "更新参数不能为空");
        }

        // 查询分类
        MchCategory category = baseMapper.selectById(id);
        if (category == null) {
            throw new BusinessException("CATEGORY_NOT_FOUND", "分类不存在");
        }

        // 检查是否已删除
        if (category.getDeleteAt() != null) {
            throw new BusinessException("CATEGORY_DELETED", "分类已被删除");
        }

        // 如果需要修改父分类ID
        if (dto.getParentId() != null && !dto.getParentId().equals(category.getParentId())) {
            // 不能将分类设置为自身的子分类
            if (dto.getParentId().equals(id)) {
                throw new BusinessException("INVALID_PARENT", "不能将分类设置为自身的子分类");
            }

            // 检查是否会造成循环引用
            if (willCauseCycle(id, dto.getParentId())) {
                throw new BusinessException("CYCLIC_REFERENCE", "不能修改父分类，会造成循环引用");
            }

            // 验证新父分类是否存在
            if (dto.getParentId() > 0) {
                MchCategory parent = baseMapper.selectById(dto.getParentId());
                if (parent == null) {
                    throw new BusinessException("PARENT_CATEGORY_NOT_FOUND", "父分类不存在");
                }
                if (parent.getDeleteAt() != null) {
                    throw new BusinessException("PARENT_CATEGORY_DELETED", "父分类已被删除");
                }

                // 计算新层级
                int newLevel = parent.getLevel() + 1;
                if (newLevel > 3) {
                    throw new BusinessException("CATEGORY_LEVEL_EXCEEDED", "分类层级不能超过3级");
                }
                category.setLevel(newLevel);
            } else {
                // 设置为顶级分类
                category.setLevel(1);
            }

            category.setParentId(dto.getParentId());
        }

        // 更新其他字段
        if (StrUtil.isNotBlank(dto.getCategoryName())) {
            category.setCategoryName(dto.getCategoryName());
        }
        if (dto.getLevel() != null) {
            category.setLevel(dto.getLevel());
        }
        if (dto.getSortOrder() != null) {
            category.setSortOrder(dto.getSortOrder());
        }
        if (dto.getStatus() != null) {
            category.setStatus(dto.getStatus());
        }
        category.setUpdateAt(LocalDateTime.now());

        baseMapper.updateById(category);

        log.info("更新商品分类成功，分类ID：{}", id);
    }

    @Override
    public void deleteCategory(Long id) {
        log.info("删除商品分类，参数：id={}", id);

        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "分类ID不能为空");
        }

        // 查询分类
        MchCategory category = baseMapper.selectById(id);
        if (category == null) {
            throw new BusinessException("CATEGORY_NOT_FOUND", "分类不存在");
        }

        // 检查是否已删除
        if (category.getDeleteAt() != null) {
            throw new BusinessException("CATEGORY_DELETED", "分类已被删除");
        }

        // 检查是否有子分类
        LambdaQueryWrapper<MchCategory> childWrapper = Wrappers.lambdaQuery();
        childWrapper.eq(MchCategory::getParentId, id);
        childWrapper.isNull(MchCategory::getDeleteAt);
        long childCount = baseMapper.selectCount(childWrapper);
        if (childCount > 0) {
            throw new BusinessException("CATEGORY_HAS_CHILDREN", "分类存在子分类，请先删除子分类");
        }

        // 检查是否有关联的商品
        LambdaQueryWrapper<MchProductCategory> productWrapper = Wrappers.lambdaQuery();
        productWrapper.eq(MchProductCategory::getCategoryId, id);
        long productCount = productCategoryMapper.selectCount(productWrapper);
        if (productCount > 0) {
            throw new BusinessException("CATEGORY_HAS_PRODUCTS", "分类关联了商品，请先解除关联");
        }

        // 软删除分类
        category.setDeleteAt(LocalDateTime.now());
        baseMapper.updateById(category);

        log.info("删除商品分类成功，分类ID：{}", id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAddCategories(CategoryBatchAddDTO dto) {
        log.info("批量新增商品分类，参数：{}", dto);

        if (dto == null || dto.getCategories() == null || dto.getCategories().isEmpty()) {
            throw new BusinessException("INVALID_PARAM", "批量新增分类参数不能为空");
        }

        for (CategoryAddDTO categoryDTO : dto.getCategories()) {
            addCategory(categoryDTO);
        }

        log.info("批量新增商品分类成功，共{}个分类", dto.getCategories().size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateCategories(CategoryBatchUpdateDTO dto) {
        log.info("批量更新商品分类，参数：{}", dto);

        if (dto == null || dto.getCategories() == null || dto.getCategories().isEmpty()) {
            throw new BusinessException("INVALID_PARAM", "批量更新分类参数不能为空");
        }

        for (CategoryUpdateDTO categoryDTO : dto.getCategories()) {
            if (categoryDTO.getId() == null) {
                throw new BusinessException("INVALID_PARAM", "批量更新时分类ID不能为空");
            }
            updateCategory(categoryDTO.getId(), categoryDTO);
        }

        log.info("批量更新商品分类成功，共{}个分类", dto.getCategories().size());
    }

    @Override
    public List<CategoryVO> getCategoryTree(Integer isGlobal, Long mchId) {
        log.info("获取完整分类树，参数：isGlobal={}, mchId={}", isGlobal, mchId);

        // 构建查询条件
        LambdaQueryWrapper<MchCategory> wrapper = Wrappers.lambdaQuery();
        wrapper.isNull(MchCategory::getDeleteAt);

        if (isGlobal != null) {
            wrapper.eq(MchCategory::getIsGlobal, isGlobal);
        }

        if (mchId != null) {
            wrapper.eq(MchCategory::getMchId, mchId);
        }

        wrapper.orderByAsc(MchCategory::getSortOrder);

        // 查询所有分类
        List<MchCategory> categories = baseMapper.selectList(wrapper);

        // 构建树形结构
        return buildCategoryTree(categories);
    }

    @Override
    public List<CategoryVO> getCategoryChildren(Long parentId) {
        log.info("获取子分类列表，参数：parentId={}", parentId);

        if (parentId == null) {
            throw new BusinessException("INVALID_PARAM", "父分类ID不能为空");
        }

        // 查询父分类是否存在
        if (parentId > 0) {
            MchCategory parent = baseMapper.selectById(parentId);
            if (parent == null) {
                throw new BusinessException("PARENT_CATEGORY_NOT_FOUND", "父分类不存在");
            }
            if (parent.getDeleteAt() != null) {
                throw new BusinessException("PARENT_CATEGORY_DELETED", "父分类已被删除");
            }
        }

        // 查询子分类
        LambdaQueryWrapper<MchCategory> wrapper = Wrappers.lambdaQuery();
        wrapper.isNull(MchCategory::getDeleteAt);
        wrapper.eq(MchCategory::getParentId, parentId);
        wrapper.orderByAsc(MchCategory::getSortOrder);

        List<MchCategory> children = baseMapper.selectList(wrapper);

        // 构建VO列表
        Map<Long, MchCategory> parentMap;
        if (parentId > 0) {
            MchCategory parent = baseMapper.selectById(parentId);
            if (parent != null) {
                parentMap = Collections.singletonMap(parent.getId(), parent);
            } else {
                parentMap = Collections.emptyMap();
            }
        } else {
            parentMap = Collections.emptyMap();
        }

        return children.stream()
                .map(category -> buildCategoryVO(category, parentMap))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCategoryStatus(Long id, CategoryStatusDTO statusDTO) {
        log.info("修改分类状态，参数：id={}, statusDTO={}", id, statusDTO);

        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "分类ID不能为空");
        }
        if (statusDTO == null || statusDTO.getStatus() == null) {
            throw new BusinessException("INVALID_PARAM", "状态不能为空");
        }

        // 查询分类
        MchCategory category = baseMapper.selectById(id);
        if (category == null) {
            throw new BusinessException("CATEGORY_NOT_FOUND", "分类不存在");
        }

        // 检查是否已删除
        if (category.getDeleteAt() != null) {
            throw new BusinessException("CATEGORY_DELETED", "分类已被删除");
        }

        // 更新状态
        category.setStatus(statusDTO.getStatus());
        category.setUpdateAt(LocalDateTime.now());
        baseMapper.updateById(category);

        log.info("修改分类状态成功，分类ID：{}，新状态：{}", id, statusDTO.getStatus());
    }

    // ========== 公共辅助方法 ==========

    /**
     * 校验分页参数
     */
    private void validatePageParams(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 1) {
            throw new BusinessException("INVALID_PARAM", "页码必须大于0");
        }
        if (pageSize == null || pageSize < 1 || pageSize > 100) {
            throw new BusinessException("INVALID_PARAM", "每页大小必须在1-100之间");
        }
    }

    /**
     * 构建分类查询条件
     */
    private LambdaQueryWrapper<MchCategory> buildCategoryWrapper(CategoryQueryDTO queryDTO) {
        LambdaQueryWrapper<MchCategory> wrapper = Wrappers.lambdaQuery();

        // 软删除过滤
        wrapper.isNull(MchCategory::getDeleteAt);

        // 分类ID
        if (queryDTO.getCategoryId() != null) {
            wrapper.eq(MchCategory::getId, queryDTO.getCategoryId());
        }

        // 分类名称模糊查询
        if (StrUtil.isNotBlank(queryDTO.getCategoryName())) {
            wrapper.like(MchCategory::getCategoryName, queryDTO.getCategoryName());
        }

        // 父级ID
        if (queryDTO.getParentId() != null) {
            wrapper.eq(MchCategory::getParentId, queryDTO.getParentId());
        }

        // 层级
        if (queryDTO.getLevel() != null) {
            wrapper.eq(MchCategory::getLevel, queryDTO.getLevel());
        }

        // 状态
        if (queryDTO.getStatus() != null) {
            wrapper.eq(MchCategory::getStatus, queryDTO.getStatus());
        }

        // 是否全局分类
        if (queryDTO.getIsGlobal() != null) {
            wrapper.eq(MchCategory::getIsGlobal, queryDTO.getIsGlobal());
        }

        // 商家ID
        if (queryDTO.getMchId() != null) {
            wrapper.eq(MchCategory::getMchId, queryDTO.getMchId());
        }

        // 排序
        if (StrUtil.isNotBlank(queryDTO.getSortField())) {
            boolean isAsc = !"desc".equalsIgnoreCase(queryDTO.getSortOrder());
            String sortField = queryDTO.getSortField().toLowerCase();

            // 根据字段名使用对应的 Lambda 表达式
            switch (sortField) {
                case "id":
                    wrapper.orderBy(true, isAsc, MchCategory::getId);
                    break;
                case "categoryname":
                case "category_name":
                    wrapper.orderBy(true, isAsc, MchCategory::getCategoryName);
                    break;
                case "parentid":
                case "parent_id":
                    wrapper.orderBy(true, isAsc, MchCategory::getParentId);
                    break;
                case "level":
                    wrapper.orderBy(true, isAsc, MchCategory::getLevel);
                    break;
                case "sortorder":
                case "sort_order":
                    if (isAsc) {
                        wrapper.orderByAsc(MchCategory::getSortOrder);
                    } else {
                        wrapper.orderByDesc(MchCategory::getSortOrder);
                    }
                    break;
                case "status":
                    wrapper.orderBy(true, isAsc, MchCategory::getStatus);
                    break;
                case "isglobal":
                case "is_global":
                    wrapper.orderBy(true, isAsc, MchCategory::getIsGlobal);
                    break;
                case "mchid":
                case "mch_id":
                    wrapper.orderBy(true, isAsc, MchCategory::getMchId);
                    break;
                case "createat":
                case "create_at":
                    if (isAsc) {
                        wrapper.orderByAsc(MchCategory::getCreateAt);
                    } else {
                        wrapper.orderByDesc(MchCategory::getCreateAt);
                    }
                    break;
                case "updateat":
                case "update_at":
                    if (isAsc) {
                        wrapper.orderByAsc(MchCategory::getUpdateAt);
                    } else {
                        wrapper.orderByDesc(MchCategory::getUpdateAt);
                    }
                    break;
                default:
                    // 默认按排序字段升序
                    wrapper.orderByAsc(MchCategory::getSortOrder);
            }
        } else {
            wrapper.orderByAsc(MchCategory::getSortOrder);
        }

        return wrapper;
    }

    /**
     * 构建分类树
     */
    private List<CategoryVO> buildCategoryTree(List<MchCategory> categories) {
        Map<Long, CategoryVO> categoryMap = new HashMap<>();

        // 构建所有节点的VO
        for (MchCategory category : categories) {
            CategoryVO vo = new CategoryVO();
            vo.setId(category.getId());
            vo.setCategoryName(category.getCategoryName());
            vo.setParentId(category.getParentId());
            vo.setLevel(category.getLevel());
            vo.setSortOrder(category.getSortOrder());
            vo.setStatus(category.getStatus());
            vo.setIsGlobal(category.getIsGlobal());
            vo.setMchId(category.getMchId());
            vo.setChildren(new ArrayList<>());
            categoryMap.put(category.getId(), vo);
        }

        // 构建树形结构
        List<CategoryVO> rootCategories = new ArrayList<>();
        for (CategoryVO vo : categoryMap.values()) {
            if (vo.getParentId() == null || vo.getParentId() == 0) {
                rootCategories.add(vo);
            } else {
                CategoryVO parent = categoryMap.get(vo.getParentId());
                if (parent != null) {
                    parent.getChildren().add(vo);
                }
            }
        }

        // 计算层级路径
        for (CategoryVO vo : categoryMap.values()) {
            vo.setLevelPath(calculateLevelPath(vo, categoryMap));
        }

        return rootCategories;
    }

    /**
     * 计算层级路径
     */
    private String calculateLevelPath(CategoryVO category, Map<Long, CategoryVO> categoryMap) {
        List<String> path = new ArrayList<>();
        CategoryVO current = category;

        while (current != null && current.getParentId() != null && current.getParentId() != 0) {
            path.add(0, current.getCategoryName());
            current = categoryMap.get(current.getParentId());
        }

        if (current != null) {
            path.add(0, current.getCategoryName());
        }

        return String.join(" > ", path);
    }

    /**
     * 检查是否会造成循环引用
     */
    private boolean willCauseCycle(Long categoryId, Long newParentId) {
        if (newParentId == null || newParentId == 0) {
            return false;
        }

        Set<Long> visited = new HashSet<>();
        Long current = newParentId;

        while (current != null && current != 0) {
            if (current.equals(categoryId)) {
                return true;
            }
            if (visited.contains(current)) {
                break;
            }
            visited.add(current);

            MchCategory parent = baseMapper.selectById(current);
            if (parent == null) {
                break;
            }
            current = parent.getParentId();
        }

        return false;
    }

    /**
     * 构建 CategoryVO
     */
    private CategoryVO buildCategoryVO(MchCategory category, Map<Long, MchCategory> parentMap) {
        CategoryVO vo = new CategoryVO();
        vo.setId(category.getId());
        vo.setCategoryName(category.getCategoryName());
        vo.setParentId(category.getParentId());
        vo.setLevel(category.getLevel());
        vo.setSortOrder(category.getSortOrder());
        vo.setStatus(category.getStatus());
        vo.setIsGlobal(category.getIsGlobal());
        vo.setMchId(category.getMchId());

        // 填充父分类名称
        if (category.getParentId() != null && category.getParentId() > 0) {
            MchCategory parent = parentMap.get(category.getParentId());
            if (parent != null) {
                vo.setParentName(parent.getCategoryName());
            }
        }

        // 计算层级路径
        Map<Long, CategoryVO> allCategories = new HashMap<>();
        allCategories.put(category.getId(), vo);
        if (category.getParentId() != null && category.getParentId() > 0) {
            MchCategory parent = parentMap.get(category.getParentId());
            if (parent != null) {
                CategoryVO parentVO = new CategoryVO();
                parentVO.setId(parent.getId());
                parentVO.setCategoryName(parent.getCategoryName());
                parentVO.setParentId(parent.getParentId());
                allCategories.put(parent.getId(), parentVO);
            }
        }
        vo.setLevelPath(calculateLevelPath(vo, allCategories));

        return vo;
    }
}