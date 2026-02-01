package com.example.service.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.service.api.dto.*;
import com.example.service.api.entity.CommissionConfig;
import com.example.service.api.entity.ErrandCategory;
import com.example.service.api.pojo.CommissionInfo;
import com.example.service.api.vo.ErrandCategoryVO;
import com.example.service.biz.mapper.CommissionConfigMapper;
import com.example.service.biz.mapper.ErrandCategoryMapper;
import com.example.service.biz.service.ErrandCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务分类服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ErrandCategoryServiceImpl extends ServiceImpl<ErrandCategoryMapper, ErrandCategory>
        implements ErrandCategoryService {

    private final CommissionConfigMapper commissionConfigMapper;

    @Override
    public PageResult<ErrandCategoryVO> listCategories(ErrandCategoryQueryDTO queryDTO) {
		// 参数校验
		Integer pageNum = queryDTO != null ? queryDTO.getPageNum() : 1;
		Integer pageSize = queryDTO != null ? queryDTO.getPageSize() : 10;
		validatePageParams(pageNum, pageSize);

		// 构建查询条件
		LambdaQueryWrapper<ErrandCategory> wrapper = buildCategoryWrapper(queryDTO);

		// 分页查询
		com.baomidou.mybatisplus.extension.plugins.pagination.Page<ErrandCategory> page =
				new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageNum, pageSize);
		com.baomidou.mybatisplus.core.metadata.IPage<ErrandCategory> pageResult = baseMapper.selectPage(page, wrapper);

		// 批量查询父分类信息
		List<Long> parentIds = pageResult.getRecords().stream()
				.map(ErrandCategory::getParentId)
				.filter(id -> id != null && id > 0)
				.distinct()
				.collect(Collectors.toList());

		Map<Long, ErrandCategory> parentMap;
		if (!parentIds.isEmpty()) {
			List<ErrandCategory> parents = baseMapper.selectBatchIds(parentIds);
			parentMap = parents.stream().collect(Collectors.toMap(ErrandCategory::getId, c -> c));
		} else {
			parentMap = new HashMap<>();
		}

		// 批量查询分佣配置
		List<Long> categoryIds = pageResult.getRecords().stream()
				.map(ErrandCategory::getId)
				.collect(Collectors.toList());
		Map<Long, CommissionInfo> commissionMap = buildCommissionInfoMap(categoryIds);

		// 组装VO列表
		List<ErrandCategoryVO> voList = pageResult.getRecords().stream()
				.map(category -> buildCategoryVO(category, parentMap, commissionMap))
				.collect(Collectors.toList());

		return new PageResult<>(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal(), voList);
	}

    @Override
    public ErrandCategoryVO getCategoryDetail(Long id) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "分类ID不能为空");
		}

		// 查询分类信息
		ErrandCategory category = baseMapper.selectById(id);
		if (category == null) {
			throw new BusinessException("CATEGORY_NOT_FOUND", "分类不存在");
		}
		if (category.getDeleteAt() != null) {
			throw new BusinessException("CATEGORY_DELETED", "分类已删除");
		}

		// 查询父分类信息
		Map<Long, ErrandCategory> parentMap = new HashMap<>();
		if (category.getParentId() != null && category.getParentId() > 0) {
			ErrandCategory parent = baseMapper.selectById(category.getParentId());
			if (parent != null) {
				parentMap.put(parent.getId(), parent);
			}
		}

		// 查询分佣配置
		Map<Long, CommissionInfo> commissionMap = buildCommissionInfoMap(Collections.singletonList(id));

		// 构建并返回VO
		return buildCategoryVO(category, parentMap, commissionMap);
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCategory(ErrandCategoryAddDTO dto) {
		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "请求参数不能为空");
		}

		// 验证父分类是否存在
		if (dto.getParentId() != null && dto.getParentId() > 0) {
			ErrandCategory parent = baseMapper.selectById(dto.getParentId());
			if (parent == null) {
				throw new BusinessException("PARENT_CATEGORY_NOT_FOUND", "父分类不存在");
			}
			if (parent.getDeleteAt() != null) {
				throw new BusinessException("PARENT_CATEGORY_DELETED", "父分类已删除");
			}

			// 验证层级（最多2级）
			if (parent.getLevel() >= 2) {
				throw new BusinessException("LEVEL_LIMIT", "分类层级不能超过2级");
			}
		}

		// 自动计算层级
		Integer level = dto.getLevel();
		if (level == null) {
			level = (dto.getParentId() != null && dto.getParentId() > 0) ? 2 : 1;
		} else if (level > 2) {
			throw new BusinessException("LEVEL_LIMIT", "分类层级不能超过2级");
		}

		// 构建实体
		ErrandCategory category = new ErrandCategory();
		category.setParentId(dto.getParentId() != null ? dto.getParentId() : 0L);
		category.setLevel(level);
		category.setCategoryName(dto.getCategoryName());
		category.setSortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 0);
		category.setAllowOfflineTrade(dto.getAllowOfflineTrade() != null ? dto.getAllowOfflineTrade() : 0);
		category.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);
		category.setCreateAt(LocalDateTime.now());
		category.setUpdateAt(LocalDateTime.now());

		// 保存分类
		baseMapper.insert(category);

		// 创建分佣配置（如果提供了分佣比例）
		if (dto.getCommissionRate() != null) {
			CommissionConfig config = new CommissionConfig();
			config.setCategoryId(category.getId());
			config.setConfigType(2); // 服务分佣
			config.setCommissionRate(dto.getCommissionRate());
			config.setStatus(1); // 启用
			config.setCreateAt(LocalDateTime.now());
			config.setUpdateAt(LocalDateTime.now());
			commissionConfigMapper.insert(config);
		}
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategory(Long id, ErrandCategoryUpdateDTO dto) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "分类ID不能为空");
		}
		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "请求参数不能为空");
		}

		// 查询分类信息
		ErrandCategory category = baseMapper.selectById(id);
		if (category == null) {
			throw new BusinessException("CATEGORY_NOT_FOUND", "分类不存在");
		}
		if (category.getDeleteAt() != null) {
			throw new BusinessException("CATEGORY_DELETED", "分类已删除");
		}

		// 检查循环引用（如果修改父分类）
		if (dto.getParentId() != null && !dto.getParentId().equals(category.getParentId())) {
			// 验证新父分类是否存在
			if (dto.getParentId() > 0) {
				ErrandCategory newParent = baseMapper.selectById(dto.getParentId());
				if (newParent == null) {
					throw new BusinessException("PARENT_CATEGORY_NOT_FOUND", "父分类不存在");
				}
				if (newParent.getDeleteAt() != null) {
					throw new BusinessException("PARENT_CATEGORY_DELETED", "父分类已删除");
				}

				// 验证层级（最多2级）
				if (newParent.getLevel() >= 2) {
					throw new BusinessException("LEVEL_LIMIT", "分类层级不能超过2级");
				}

				// 检测循环引用
				if (willCauseCycle(id, dto.getParentId())) {
					throw new BusinessException("CYCLE_REFERENCE", "修改父分类会造成循环引用");
				}
			}
		}

		// 更新字段
		if (dto.getCategoryName() != null && !dto.getCategoryName().trim().isEmpty()) {
			category.setCategoryName(dto.getCategoryName().trim());
		}
		if (dto.getParentId() != null) {
			category.setParentId(dto.getParentId());
			// 自动计算层级
			category.setLevel(dto.getParentId() > 0 ? 2 : 1);
		}
		if (dto.getSortOrder() != null) {
			category.setSortOrder(dto.getSortOrder());
		}
		if (dto.getAllowOfflineTrade() != null) {
			category.setAllowOfflineTrade(dto.getAllowOfflineTrade());
		}
		if (dto.getStatus() != null) {
			category.setStatus(dto.getStatus());
		}
		category.setUpdateAt(LocalDateTime.now());

		// 更新分类
		baseMapper.updateById(category);

		// 更新分佣配置
		if (dto.getCommissionRate() != null) {
			// 先删除旧配置（软删除）
			LambdaQueryWrapper<CommissionConfig> wrapper = Wrappers.lambdaQuery();
			wrapper.eq(CommissionConfig::getCategoryId, id);
			wrapper.eq(CommissionConfig::getConfigType, 2);
			wrapper.isNull(CommissionConfig::getDeleteAt);
			CommissionConfig oldConfig = commissionConfigMapper.selectOne(wrapper);
			if (oldConfig != null) {
				oldConfig.setDeleteAt(LocalDateTime.now());
				commissionConfigMapper.updateById(oldConfig);
			}

			// 创建新配置
			CommissionConfig newConfig = new CommissionConfig();
			newConfig.setCategoryId(id);
			newConfig.setConfigType(2); // 服务分佣
			newConfig.setCommissionRate(dto.getCommissionRate());
			newConfig.setStatus(1); // 启用
			newConfig.setCreateAt(LocalDateTime.now());
			newConfig.setUpdateAt(LocalDateTime.now());
			commissionConfigMapper.insert(newConfig);
		}
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategory(Long id) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "分类ID不能为空");
		}

		// 查询分类信息
		ErrandCategory category = baseMapper.selectById(id);
		if (category == null) {
			throw new BusinessException("CATEGORY_NOT_FOUND", "分类不存在");
		}
		if (category.getDeleteAt() != null) {
			throw new BusinessException("CATEGORY_DELETED", "分类已删除");
		}

		// 检查是否有子分类
		LambdaQueryWrapper<ErrandCategory> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(ErrandCategory::getParentId, id);
		wrapper.isNull(ErrandCategory::getDeleteAt);
		Long childCount = baseMapper.selectCount(wrapper);
		if (childCount != null && childCount > 0) {
			throw new BusinessException("HAS_CHILDREN", "该分类下有子分类，不能删除");
		}


		// 软删除分类
		category.setDeleteAt(LocalDateTime.now());
		category.setUpdateAt(LocalDateTime.now());
		baseMapper.updateById(category);

		// 级联软删除分佣配置
		LambdaQueryWrapper<CommissionConfig> configWrapper = Wrappers.lambdaQuery();
		configWrapper.eq(CommissionConfig::getCategoryId, id);
		configWrapper.isNull(CommissionConfig::getDeleteAt);
		List<CommissionConfig> configs = commissionConfigMapper.selectList(configWrapper);
		for (CommissionConfig config : configs) {
			config.setDeleteAt(LocalDateTime.now());
			config.setUpdateAt(LocalDateTime.now());
			commissionConfigMapper.updateById(config);
		}
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAddCategories(ErrandCategoryBatchAddDTO dto) {
		if (dto == null || dto.getCategories() == null || dto.getCategories().isEmpty()) {
			throw new BusinessException("INVALID_PARAM", "批量新增数据不能为空");
		}

		for (ErrandCategoryAddDTO categoryDto : dto.getCategories()) {
			addCategory(categoryDto);
		}
	}

    @Override
    public List<ErrandCategoryVO> getCategoryTree() {
		// 查询所有未删除的分类
		LambdaQueryWrapper<ErrandCategory> wrapper = Wrappers.lambdaQuery();
		wrapper.isNull(ErrandCategory::getDeleteAt);
		wrapper.orderByAsc(ErrandCategory::getSortOrder);
		List<ErrandCategory> allCategories = baseMapper.selectList(wrapper);

		// 构建父分类Map
		Map<Long, ErrandCategory> parentMap = new HashMap<>();
		for (ErrandCategory category : allCategories) {
			parentMap.put(category.getId(), category);
		}

		// 批量查询分佣配置
		List<Long> categoryIds = allCategories.stream()
				.map(ErrandCategory::getId)
				.collect(Collectors.toList());
		Map<Long, CommissionInfo> commissionMap = buildCommissionInfoMap(categoryIds);

		// 构建树形结构
		return buildCategoryTree(allCategories, parentMap, commissionMap);
	}

    @Override
    public List<ErrandCategoryVO> getCategoryChildren(Long parentId) {
		if (parentId == null) {
			throw new BusinessException("INVALID_PARAM", "父分类ID不能为空");
		}

		// 如果parentId > 0，验证父分类存在且未删除
		if (parentId > 0) {
			ErrandCategory parent = baseMapper.selectById(parentId);
			if (parent == null) {
				throw new BusinessException("PARENT_CATEGORY_NOT_FOUND", "父分类不存在");
			}
			if (parent.getDeleteAt() != null) {
				throw new BusinessException("PARENT_CATEGORY_DELETED", "父分类已删除");
			}
		}

		// 查询子分类
		LambdaQueryWrapper<ErrandCategory> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(ErrandCategory::getParentId, parentId);
		wrapper.isNull(ErrandCategory::getDeleteAt);
		wrapper.orderByAsc(ErrandCategory::getSortOrder);
		List<ErrandCategory> children = baseMapper.selectList(wrapper);

		if (children.isEmpty()) {
			return Collections.emptyList();
		}

		// 构建父分类Map
		Map<Long, ErrandCategory> parentMap = new HashMap<>();
		for (ErrandCategory category : children) {
			parentMap.put(category.getId(), category);
		}
		if (parentId > 0) {
			ErrandCategory parent = baseMapper.selectById(parentId);
			if (parent != null) {
				parentMap.put(parent.getId(), parent);
			}
		}

		// 批量查询分佣配置
		List<Long> categoryIds = children.stream()
				.map(ErrandCategory::getId)
				.collect(Collectors.toList());
		Map<Long, CommissionInfo> commissionMap = buildCommissionInfoMap(categoryIds);

		// 转换为VO列表
		return children.stream()
				.map(category -> buildCategoryVO(category, parentMap, commissionMap))
				.collect(Collectors.toList());
	}

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
	 * 构建查询条件
	 */
	private LambdaQueryWrapper<ErrandCategory> buildCategoryWrapper(ErrandCategoryQueryDTO queryDTO) {
		LambdaQueryWrapper<ErrandCategory> wrapper = Wrappers.lambdaQuery();
		wrapper.isNull(ErrandCategory::getDeleteAt);

		if (queryDTO != null) {
			if (queryDTO.getCategoryId() != null) {
				wrapper.eq(ErrandCategory::getId, queryDTO.getCategoryId());
			}
			if (queryDTO.getParentId() != null) {
				wrapper.eq(ErrandCategory::getParentId, queryDTO.getParentId());
			}
			if (queryDTO.getLevel() != null) {
				wrapper.eq(ErrandCategory::getLevel, queryDTO.getLevel());
			}
			if (queryDTO.getCategoryName() != null && !queryDTO.getCategoryName().trim().isEmpty()) {
				wrapper.like(ErrandCategory::getCategoryName, queryDTO.getCategoryName().trim());
			}
			if (queryDTO.getStatus() != null) {
				wrapper.eq(ErrandCategory::getStatus, queryDTO.getStatus());
			}
			if (queryDTO.getAllowOfflineTrade() != null) {
				wrapper.eq(ErrandCategory::getAllowOfflineTrade, queryDTO.getAllowOfflineTrade());
			}

			// 排序
			if (queryDTO.getSortField() != null && !queryDTO.getSortField().trim().isEmpty()) {
				boolean isAsc = "asc".equalsIgnoreCase(queryDTO.getSortOrder());
				switch (queryDTO.getSortField().trim().toLowerCase()) {
					case "sortorder":
						wrapper.orderBy(true, isAsc, ErrandCategory::getSortOrder);
						break;
					case "categoryname":
						wrapper.orderBy(true, isAsc, ErrandCategory::getCategoryName);
						break;
					case "createtime":
						wrapper.orderBy(true, isAsc, ErrandCategory::getCreateAt);
						break;
					default:
						wrapper.orderByAsc(ErrandCategory::getSortOrder);
				}
			} else {
				wrapper.orderByAsc(ErrandCategory::getSortOrder);
			}
		} else {
			wrapper.orderByAsc(ErrandCategory::getSortOrder);
		}

		return wrapper;
	}

	/**
	 * 构建分类VO
	 */
	private ErrandCategoryVO buildCategoryVO(ErrandCategory category, Map<Long, ErrandCategory> parentMap,
											 Map<Long, CommissionInfo> commissionMap) {
		ErrandCategoryVO vo = ErrandCategoryVO.builder()
				.id(category.getId())
				.parentId(category.getParentId())
				.level(category.getLevel())
				.categoryName(category.getCategoryName())
				.sortOrder(category.getSortOrder())
				.allowOfflineTrade(category.getAllowOfflineTrade())
				.status(category.getStatus())
				.createAt(category.getCreateAt())
				.updateAt(category.getUpdateAt())
				.build();

		// 填充父分类名称
		if (category.getParentId() != null && category.getParentId() > 0) {
			ErrandCategory parent = parentMap.get(category.getParentId());
			if (parent != null) {
				vo.setParentName(parent.getCategoryName());
			}
		}

		// 计算层级路径
		vo.setLevelPath(calculateLevelPath(category, parentMap));

		// 填充分佣配置信息
		vo.setCommissionInfo(commissionMap.get(category.getId()));

		return vo;
	}

	/**
	 * 计算层级路径
	 */
	private String calculateLevelPath(ErrandCategory category, Map<Long, ErrandCategory> parentMap) {
		List<String> pathList = new ArrayList<>();
		Map<Long, ErrandCategory> tempMap = new HashMap<>(parentMap); // 创建临时map
		Long currentId = category.getId();

		while (currentId != null && currentId > 0) {
			ErrandCategory current = tempMap.get(currentId);
			if (current == null) {
				break;
			}
			pathList.add(0, current.getCategoryName());
			currentId = current.getParentId();
			if (currentId != null && currentId > 0 && !tempMap.containsKey(currentId)) {
				// 如果父节点不在map中，查询数据库
				ErrandCategory parent = baseMapper.selectById(currentId);
				if (parent != null) {
					tempMap.put(parent.getId(), parent);
				} else {
					break;
				}
			}
		}

		return String.join(" > ", pathList);
	}

	/**
	 * 检测是否会造成循环引用
	 */
	private boolean willCauseCycle(Long categoryId, Long newParentId) {
		if (newParentId == null || newParentId == 0) {
			return false;
		}

		if (newParentId.equals(categoryId)) {
			return true;
		}

		Set<Long> visited = new HashSet<>();
		Long currentId = newParentId;

		while (currentId != null && currentId > 0) {
			if (visited.contains(currentId)) {
				return true; // 检测到循环
			}
			visited.add(currentId);

			ErrandCategory current = baseMapper.selectById(currentId);
			if (current == null) {
				break;
			}

			if (current.getId().equals(categoryId)) {
				return true; // 找到了当前节点，会造成循环
			}

			currentId = current.getParentId();
		}

		return false;
	}

	/**
	 * 构建分佣配置信息
	 */
	private Map<Long, CommissionInfo> buildCommissionInfoMap(List<Long> categoryIds) {
		if (categoryIds == null || categoryIds.isEmpty()) {
			return Collections.emptyMap();
		}

		LambdaQueryWrapper<CommissionConfig> wrapper = Wrappers.lambdaQuery();
		wrapper.in(CommissionConfig::getCategoryId, categoryIds);
		wrapper.eq(CommissionConfig::getConfigType, 2); // 服务分佣
		wrapper.eq(CommissionConfig::getStatus, 1); // 启用
		wrapper.isNull(CommissionConfig::getDeleteAt);

		List<CommissionConfig> configs = commissionConfigMapper.selectList(wrapper);

		return configs.stream().collect(Collectors.toMap(
				CommissionConfig::getCategoryId,
				config -> CommissionInfo.builder()
						.configType(config.getConfigType())
						.commissionRate(config.getCommissionRate())
						.status(config.getStatus())
						.build(),
				(v1, v2) -> v1
		));
	}

	/**
	 * 构建分类树
	 */
	private List<ErrandCategoryVO> buildCategoryTree(List<ErrandCategory> allCategories,
													  Map<Long, ErrandCategory> parentMap,
													  Map<Long, CommissionInfo> commissionMap) {
		Map<Long, ErrandCategoryVO> voMap = new HashMap<>();

		// 创建所有节点的VO
		for (ErrandCategory category : allCategories) {
			ErrandCategoryVO vo = buildCategoryVO(category, parentMap, commissionMap);
			vo.setChildren(new ArrayList<>());
			voMap.put(category.getId(), vo);
		}

		// 构建树形结构
		List<ErrandCategoryVO> roots = new ArrayList<>();
		for (ErrandCategoryVO vo : voMap.values()) {
			if (vo.getParentId() == null || vo.getParentId() == 0) {
				roots.add(vo);
			} else {
				ErrandCategoryVO parent = voMap.get(vo.getParentId());
				if (parent != null) {
					parent.getChildren().add(vo);
				}
			}
		}

		return roots;
	}
}