package com.example.merchant.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.BusinessException;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigAddDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigBatchAddDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigQueryDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigStatusDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigUpdateDTO;
import com.example.merchant.api.entity.DeliveryFeeConfig;
import com.example.merchant.api.entity.DeliveryFeeRule;
import com.example.merchant.api.vo.DeliveryFeeConfigVO;
import com.example.merchant.api.vo.DeliveryFeeRuleVO;
import com.example.merchant.biz.mapper.DeliveryFeeConfigMapper;
import com.example.merchant.biz.mapper.DeliveryFeeRuleMapper;
import com.example.merchant.biz.service.DeliveryFeeConfigService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 配送费配置服务实现类
 *
 * @author campus-merchant
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryFeeConfigServiceImpl extends ServiceImpl<DeliveryFeeConfigMapper, DeliveryFeeConfig>
		implements DeliveryFeeConfigService {

	private final DeliveryFeeRuleMapper deliveryFeeRuleMapper;

	@Override
	public PageResult<DeliveryFeeConfigVO> listConfigs(DeliveryFeeConfigQueryDTO queryDTO) {
		log.info("分页查询配送费配置列表，参数：{}", queryDTO);

		// 参数校验
		validatePageParams(queryDTO.getPageNum(), queryDTO.getPageSize());

		// 构建查询条件
		LambdaQueryWrapper<DeliveryFeeConfig> wrapper = buildConfigWrapper(queryDTO);

		// 执行分页查询
		IPage<DeliveryFeeConfig> page = baseMapper.selectPage(
				new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()), wrapper);

		if (page.getRecords().isEmpty()) {
			return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), Collections.emptyList());
		}

		// 批量查询规则
		List<Long> configIds = page.getRecords().stream().map(DeliveryFeeConfig::getId).collect(Collectors.toList());
		Map<Long, List<DeliveryFeeRule>> ruleMap = batchQueryRulesByConfigIds(configIds);

		// 组装VO对象
		List<DeliveryFeeConfigVO> voList = page.getRecords().stream()
			.map(config -> buildConfigVO(config, ruleMap))
			.collect(Collectors.toList());

		return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), voList);
	}

	@Override
	public DeliveryFeeConfigVO getConfigDetail(Long id) {
		log.info("查询配送费配置详情，参数：id={}", id);

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "配置ID不能为空");
		}

		// 查询配置
		DeliveryFeeConfig config = baseMapper.selectById(id);
		if (config == null) {
			throw new BusinessException("CONFIG_NOT_FOUND", "配送费配置不存在");
		}

		if (config.getDeleteAt() != null) {
			throw new BusinessException("CONFIG_DELETED", "配送费配置已被删除");
		}

		// 查询规则
		Map<Long, List<DeliveryFeeRule>> ruleMap = batchQueryRulesByConfigIds(Collections.singletonList(id));

		// 组装VO
		return buildConfigVO(config, ruleMap);
	}

	@Override
	public void addConfig(DeliveryFeeConfigAddDTO dto) {
		log.info("新增配送费配置，参数：{}", dto);

		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "新增配置参数不能为空");
		}

		// 构建配置实体
		DeliveryFeeConfig config = new DeliveryFeeConfig();
		config.setConfigName(dto.getConfigName());
		config.setBaseFee(dto.getBaseFee());
		config.setBaseDistance(dto.getBaseDistance());
		config.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);
		config.setCreateAt(LocalDateTime.now());
		config.setUpdateAt(LocalDateTime.now());

		// 保存配置
		baseMapper.insert(config);

		log.info("新增配送费配置成功，配置ID：{}", config.getId());
	}

	@Override
	public void updateConfig(Long id, DeliveryFeeConfigUpdateDTO dto) {
		log.info("更新配送费配置，参数：id={}, dto={}", id, dto);

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "配置ID不能为空");
		}

		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "更新配置参数不能为空");
		}

		// 查询配置
		DeliveryFeeConfig config = baseMapper.selectById(id);
		if (config == null) {
			throw new BusinessException("CONFIG_NOT_FOUND", "配送费配置不存在");
		}

		if (config.getDeleteAt() != null) {
			throw new BusinessException("CONFIG_DELETED", "配送费配置已被删除");
		}

		// 更新字段
		if (StrUtil.isNotBlank(dto.getConfigName())) {
			config.setConfigName(dto.getConfigName());
		}
		if (dto.getBaseFee() != null) {
			config.setBaseFee(dto.getBaseFee());
		}
		if (dto.getBaseDistance() != null) {
			config.setBaseDistance(dto.getBaseDistance());
		}
		if (dto.getStatus() != null) {
			config.setStatus(dto.getStatus());
		}
		config.setUpdateAt(LocalDateTime.now());

		// 更新配置
		baseMapper.updateById(config);

		log.info("更新配送费配置成功，配置ID：{}", id);
	}

	@Override
	public void deleteConfig(Long id) {
		log.info("删除配送费配置，参数：id={}", id);

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "配置ID不能为空");
		}

		// 查询配置
		DeliveryFeeConfig config = baseMapper.selectById(id);
		if (config == null) {
			throw new BusinessException("CONFIG_NOT_FOUND", "配送费配置不存在");
		}

		if (config.getDeleteAt() != null) {
			throw new BusinessException("CONFIG_DELETED", "配送费配置已被删除");
		}

		// 检查是否有关联的规则
		LambdaQueryWrapper<DeliveryFeeRule> ruleWrapper = Wrappers.lambdaQuery();
		ruleWrapper.eq(DeliveryFeeRule::getConfigId, id);
		ruleWrapper.isNull(DeliveryFeeRule::getDeleteAt);
		long ruleCount = deliveryFeeRuleMapper.selectCount(ruleWrapper);
		if (ruleCount > 0) {
			throw new BusinessException("CONFIG_HAS_RULES", "配送费配置存在关联规则，请先删除规则");
		}

		// 软删除配置
		config.setDeleteAt(LocalDateTime.now());
		baseMapper.updateById(config);

		log.info("删除配送费配置成功，配置ID：{}", id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void batchAddConfigs(DeliveryFeeConfigBatchAddDTO dto) {
		log.info("批量新增配送费配置，参数：{}", dto);

		if (dto == null || dto.getConfigs() == null || dto.getConfigs().isEmpty()) {
			throw new BusinessException("INVALID_PARAM", "批量新增配置参数不能为空");
		}

		for (DeliveryFeeConfigAddDTO configDTO : dto.getConfigs()) {
			addConfig(configDTO);
		}

		log.info("批量新增配送费配置成功，共{}个配置", dto.getConfigs().size());
	}

	@Override
	public void updateConfigStatus(Long id, DeliveryFeeConfigStatusDTO statusDTO) {
		log.info("修改配送费配置状态，参数：id={}, statusDTO={}", id, statusDTO);

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "配置ID不能为空");
		}

		if (statusDTO == null || statusDTO.getStatus() == null) {
			throw new BusinessException("INVALID_PARAM", "状态参数不能为空");
		}

		// 查询配置
		DeliveryFeeConfig config = baseMapper.selectById(id);
		if (config == null) {
			throw new BusinessException("CONFIG_NOT_FOUND", "配送费配置不存在");
		}

		if (config.getDeleteAt() != null) {
			throw new BusinessException("CONFIG_DELETED", "配送费配置已被删除");
		}

		// 更新状态
		config.setStatus(statusDTO.getStatus());
		config.setUpdateAt(LocalDateTime.now());
		baseMapper.updateById(config);

		log.info("修改配送费配置状态成功，配置ID：{}，新状态：{}", id, statusDTO.getStatus());
	}

	/**
	 * 批量查询规则并按configId分组
	 */
	private Map<Long, List<DeliveryFeeRule>> batchQueryRulesByConfigIds(List<Long> configIds) {
		if (configIds == null || configIds.isEmpty()) {
			return Collections.emptyMap();
		}

		LambdaQueryWrapper<DeliveryFeeRule> wrapper = Wrappers.lambdaQuery();
		wrapper.in(DeliveryFeeRule::getConfigId, configIds);
		wrapper.isNull(DeliveryFeeRule::getDeleteAt);
		wrapper.orderByAsc(DeliveryFeeRule::getSortOrder);

		List<DeliveryFeeRule> rules = deliveryFeeRuleMapper.selectList(wrapper);

		return rules.stream().collect(Collectors.groupingBy(DeliveryFeeRule::getConfigId));
	}

	/**
	 * 构建DeliveryFeeConfigVO
	 */
	private DeliveryFeeConfigVO buildConfigVO(DeliveryFeeConfig config, Map<Long, List<DeliveryFeeRule>> ruleMap) {
		DeliveryFeeConfigVO vo = new DeliveryFeeConfigVO();
		vo.setId(config.getId());
		vo.setConfigName(config.getConfigName());
		vo.setBaseFee(config.getBaseFee());
		vo.setBaseDistance(config.getBaseDistance());
		vo.setStatus(config.getStatus());
		vo.setCreateAt(config.getCreateAt());
		vo.setUpdateAt(config.getUpdateAt());

		// 查询并转换规则
		List<DeliveryFeeRule> rules = ruleMap.getOrDefault(config.getId(), Collections.emptyList());
		List<DeliveryFeeRuleVO> ruleVOs = rules.stream().map(this::buildRuleVO).collect(Collectors.toList());
		vo.setRules(ruleVOs);

		return vo;
	}

	/**
	 * 构建DeliveryFeeRuleVO
	 */
	private DeliveryFeeRuleVO buildRuleVO(DeliveryFeeRule rule) {
		DeliveryFeeRuleVO vo = new DeliveryFeeRuleVO();
		vo.setId(rule.getId());
		vo.setConfigId(rule.getConfigId());
		vo.setRuleType(rule.getRuleType());
		vo.setRuleTypeName(rule.getRuleType() == 1 ? "距离规则" : "时间规则");
		vo.setDistanceStart(rule.getDistanceStart());
		vo.setDistanceEnd(rule.getDistanceEnd());
		vo.setPricePerKm(rule.getPricePerKm());
		vo.setTimeStart(rule.getTimeStart());
		vo.setTimeEnd(rule.getTimeEnd());
		vo.setTimeFee(rule.getTimeFee());
		vo.setTimeType(rule.getTimeType());
		vo.setTimeTypeName(rule.getTimeType() != null ? (rule.getTimeType() == 1 ? "白天" : "夜间") : null);
		vo.setSortOrder(rule.getSortOrder());
		vo.setCreateAt(rule.getCreateAt());
		vo.setUpdateAt(rule.getUpdateAt());
		return vo;
	}

	/**
	 * 构建查询条件
	 */
	private LambdaQueryWrapper<DeliveryFeeConfig> buildConfigWrapper(DeliveryFeeConfigQueryDTO queryDTO) {
		LambdaQueryWrapper<DeliveryFeeConfig> wrapper = Wrappers.lambdaQuery();

		// 软删除过滤
		wrapper.isNull(DeliveryFeeConfig::getDeleteAt);

		// 配置ID
		if (queryDTO.getConfigId() != null) {
			wrapper.eq(DeliveryFeeConfig::getId, queryDTO.getConfigId());
		}

		// 配置名称模糊查询
		if (StrUtil.isNotBlank(queryDTO.getConfigName())) {
			wrapper.like(DeliveryFeeConfig::getConfigName, queryDTO.getConfigName());
		}

		// 状态筛选
		if (queryDTO.getStatus() != null) {
			wrapper.eq(DeliveryFeeConfig::getStatus, queryDTO.getStatus());
		}

		// 排序
		String sortField = StrUtil.isNotBlank(queryDTO.getSortField()) ? queryDTO.getSortField() : "createAt";
		String sortOrder = StrUtil.isNotBlank(queryDTO.getSortOrder()) ? queryDTO.getSortOrder() : "desc";
		boolean isAsc = "asc".equalsIgnoreCase(sortOrder);

		// 根据排序字段动态构建排序条件
		switch (sortField) {
			case "createAt":
				wrapper.orderBy(true, isAsc, DeliveryFeeConfig::getCreateAt);
				break;
			case "updateAt":
				wrapper.orderBy(true, isAsc, DeliveryFeeConfig::getUpdateAt);
				break;
			case "configName":
				wrapper.orderBy(true, isAsc, DeliveryFeeConfig::getConfigName);
				break;
			case "baseFee":
				wrapper.orderBy(true, isAsc, DeliveryFeeConfig::getBaseFee);
				break;
			case "baseDistance":
				wrapper.orderBy(true, isAsc, DeliveryFeeConfig::getBaseDistance);
				break;
			case "status":
				wrapper.orderBy(true, isAsc, DeliveryFeeConfig::getStatus);
				break;
			default:
				wrapper.orderBy(true, isAsc, DeliveryFeeConfig::getCreateAt);
				break;
		}

		return wrapper;
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

}