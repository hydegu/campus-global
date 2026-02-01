package com.example.merchant.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.BusinessException;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleAddDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleQueryDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleStatusDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleUpdateDTO;
import com.example.merchant.api.entity.DeliveryFeeConfig;
import com.example.merchant.api.entity.DeliveryFeeRule;
import com.example.merchant.api.vo.DeliveryFeeRuleVO;
import com.example.merchant.biz.mapper.DeliveryFeeConfigMapper;
import com.example.merchant.biz.mapper.DeliveryFeeRuleMapper;
import com.example.merchant.biz.service.DeliveryFeeRuleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 配送费规则服务实现类
 *
 * @author campus-merchant
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryFeeRuleServiceImpl extends ServiceImpl<DeliveryFeeRuleMapper, DeliveryFeeRule>
		implements DeliveryFeeRuleService {

	private final DeliveryFeeConfigMapper deliveryFeeConfigMapper;

	@Override
	public PageResult<DeliveryFeeRuleVO> listRules(DeliveryFeeRuleQueryDTO queryDTO) {
		log.info("分页查询配送费规则列表，参数：{}", queryDTO);

		// 参数校验
		validatePageParams(queryDTO.getPageNum(), queryDTO.getPageSize());

		// 构建查询条件
		LambdaQueryWrapper<DeliveryFeeRule> wrapper = buildRuleWrapper(queryDTO);

		// 执行分页查询
		IPage<DeliveryFeeRule> page = baseMapper.selectPage(
				new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()), wrapper);

		// 转换为VO
		List<DeliveryFeeRuleVO> voList = page.getRecords().stream().map(this::buildRuleVO).collect(Collectors.toList());

		return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), voList);
	}

	@Override
	public DeliveryFeeRuleVO getRuleDetail(Integer id) {
		log.info("查询配送费规则详情，参数：id={}", id);

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "规则ID不能为空");
		}

		// 查询规则
		DeliveryFeeRule rule = baseMapper.selectById(id);
		if (rule == null) {
			throw new BusinessException("RULE_NOT_FOUND", "配送费规则不存在");
		}

		if (rule.getDeleteAt() != null) {
			throw new BusinessException("RULE_DELETED", "配送费规则已被删除");
		}

		// 转换为VO
		return buildRuleVO(rule);
	}

	@Override
	public void addRule(DeliveryFeeRuleAddDTO dto) {
		log.info("新增配送费规则，参数：{}", dto);

		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "新增规则参数不能为空");
		}

		// 校验配置是否存在
		validateConfigExists(dto.getConfigId());

		// 根据规则类型校验必填字段
		if (dto.getRuleType() == 1) {
			validateDistanceRule(dto);
		}
		else if (dto.getRuleType() == 2) {
			validateTimeRule(dto);
		}
		else {
			throw new BusinessException("INVALID_PARAM", "规则类型必须是1或2");
		}

		// 业务校验：规则类型一致性
		validateRuleTypeConsistency(dto.getConfigId(), dto.getRuleType());

		// 业务校验：根据规则类型进行重叠和覆盖范围校验
		if (dto.getRuleType() == 1) {
			// 距离规则：校验重叠
			validateDistanceRuleOverlap(dto.getConfigId(), dto.getDistanceStart(), dto.getDistanceEnd(), null);
		}
		else if (dto.getRuleType() == 2) {
			// 时间规则：校验重叠
			validateTimeRuleOverlap(dto.getConfigId(), dto.getTimeStart(), dto.getTimeEnd(), null);
		}

		// 构建规则实体
		DeliveryFeeRule rule = new DeliveryFeeRule();
		rule.setConfigId(dto.getConfigId());
		rule.setRuleType(dto.getRuleType());
		rule.setDistanceStart(dto.getDistanceStart());
		rule.setDistanceEnd(dto.getDistanceEnd());
		rule.setPricePerKm(dto.getPricePerKm());
		rule.setTimeStart(dto.getTimeStart());
		rule.setTimeEnd(dto.getTimeEnd());
		rule.setTimeFee(dto.getTimeFee());
		rule.setTimeType(dto.getTimeType());
		rule.setSortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 0);
		rule.setCreateAt(LocalDateTime.now());
		rule.setUpdateAt(LocalDateTime.now());

		// 保存规则
		baseMapper.insert(rule);

		// 业务校验：覆盖范围校验（在保存后进行，因为需要检查所有规则）
		if (dto.getRuleType() == 1) {
			validateDistanceRuleCoverage(dto.getConfigId());
		}
		else if (dto.getRuleType() == 2) {
			validateTimeRuleCoverage(dto.getConfigId());
		}

		log.info("新增配送费规则成功，规则ID：{}", rule.getId());
	}

	@Override
	public void updateRule(Integer id, DeliveryFeeRuleUpdateDTO dto) {
		log.info("更新配送费规则，参数：id={}, dto={}", id, dto);

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "规则ID不能为空");
		}

		if (dto == null) {
			throw new BusinessException("INVALID_PARAM", "更新规则参数不能为空");
		}

		// 查询规则
		DeliveryFeeRule rule = baseMapper.selectById(id);
		if (rule == null) {
			throw new BusinessException("RULE_NOT_FOUND", "配送费规则不存在");
		}

		if (rule.getDeleteAt() != null) {
			throw new BusinessException("RULE_DELETED", "配送费规则已被删除");
		}

		// 如果修改了规则类型，需要重新校验
		if (dto.getRuleType() != null && !dto.getRuleType().equals(rule.getRuleType())) {
			// 业务校验：规则类型一致性
			validateRuleTypeConsistency(rule.getConfigId(), dto.getRuleType());

			rule.setRuleType(dto.getRuleType());
			// 清空不相关字段
			if (dto.getRuleType() == 1) {
				rule.setTimeStart(null);
				rule.setTimeEnd(null);
				rule.setTimeFee(null);
				rule.setTimeType(null);
			}
			else if (dto.getRuleType() == 2) {
				rule.setDistanceStart(null);
				rule.setDistanceEnd(null);
				rule.setPricePerKm(null);
			}
		}

		// 根据规则类型更新字段
		if (rule.getRuleType() == 1) {
			if (dto.getDistanceStart() != null) {
				rule.setDistanceStart(dto.getDistanceStart());
			}
			if (dto.getDistanceEnd() != null) {
				rule.setDistanceEnd(dto.getDistanceEnd());
			}
			if (dto.getPricePerKm() != null) {
				rule.setPricePerKm(dto.getPricePerKm());
			}
			validateDistanceRuleFields(rule);

			// 业务校验：距离规则重叠（排除当前规则）
			if (rule.getDistanceStart() != null && rule.getDistanceEnd() != null) {
				validateDistanceRuleOverlap(rule.getConfigId(), rule.getDistanceStart(), rule.getDistanceEnd(), id);
			}
		}
		else if (rule.getRuleType() == 2) {
			if (dto.getTimeStart() != null) {
				rule.setTimeStart(dto.getTimeStart());
			}
			if (dto.getTimeEnd() != null) {
				rule.setTimeEnd(dto.getTimeEnd());
			}
			if (dto.getTimeFee() != null) {
				rule.setTimeFee(dto.getTimeFee());
			}
			if (dto.getTimeType() != null) {
				rule.setTimeType(dto.getTimeType());
			}
			validateTimeRuleFields(rule);

			// 业务校验：时间规则重叠（排除当前规则）
			if (rule.getTimeStart() != null && rule.getTimeEnd() != null) {
				validateTimeRuleOverlap(rule.getConfigId(), rule.getTimeStart(), rule.getTimeEnd(), id);
			}
		}

		// 更新排序
		if (dto.getSortOrder() != null) {
			rule.setSortOrder(dto.getSortOrder());
		}

		rule.setUpdateAt(LocalDateTime.now());

		// 更新规则
		baseMapper.updateById(rule);

		// 业务校验：覆盖范围校验（在更新后进行）
		if (rule.getRuleType() == 1) {
			validateDistanceRuleCoverage(rule.getConfigId());
		}
		else if (rule.getRuleType() == 2) {
			validateTimeRuleCoverage(rule.getConfigId());
		}

		log.info("更新配送费规则成功，规则ID：{}", id);
	}

	@Override
	public void deleteRule(Integer id) {
		log.info("删除配送费规则，参数：id={}", id);

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "规则ID不能为空");
		}

		// 查询规则
		DeliveryFeeRule rule = baseMapper.selectById(id);
		if (rule == null) {
			throw new BusinessException("RULE_NOT_FOUND", "配送费规则不存在");
		}

		if (rule.getDeleteAt() != null) {
			throw new BusinessException("RULE_DELETED", "配送费规则已被删除");
		}

		// 软删除规则
		rule.setDeleteAt(LocalDateTime.now());
		baseMapper.updateById(rule);

		log.info("删除配送费规则成功，规则ID：{}", id);
	}

	@Override
	public void updateRuleStatus(Integer id, DeliveryFeeRuleStatusDTO statusDTO) {
		log.info("修改配送费规则状态，参数：id={}, statusDTO={}", id, statusDTO);

		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "规则ID不能为空");
		}

		if (statusDTO == null || statusDTO.getStatus() == null) {
			throw new BusinessException("INVALID_PARAM", "状态参数不能为空");
		}

		// 查询规则
		DeliveryFeeRule rule = baseMapper.selectById(id);
		if (rule == null) {
			throw new BusinessException("RULE_NOT_FOUND", "配送费规则不存在");
		}

		if (rule.getDeleteAt() != null) {
			throw new BusinessException("RULE_DELETED", "配送费规则已被删除");
		}

		// 注意：规则本身没有status字段，这里我们通过软删除来模拟禁用
		if (statusDTO.getStatus() == 0) {
			// 禁用 = 软删除
			rule.setDeleteAt(LocalDateTime.now());
			baseMapper.updateById(rule);
			log.info("禁用配送费规则成功，规则ID：{}", id);
		}
		else {
			// 启用 = 恢复（如果之前被软删除）
			if (rule.getDeleteAt() != null) {
				throw new BusinessException("RULE_DELETED", "配送费规则已被删除，无法启用");
			}
			log.info("启用配送费规则成功，规则ID：{}", id);
		}
	}

	@Override
	public List<DeliveryFeeRuleVO> listRulesByConfigId(Long configId) {
		log.info("查询配置的规则列表，参数：configId={}", configId);

		if (configId == null) {
			throw new BusinessException("INVALID_PARAM", "配置ID不能为空");
		}

		// 校验配置是否存在
		validateConfigExists(configId);

		// 查询规则
		LambdaQueryWrapper<DeliveryFeeRule> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(DeliveryFeeRule::getConfigId, configId);
		wrapper.isNull(DeliveryFeeRule::getDeleteAt);
		wrapper.orderByAsc(DeliveryFeeRule::getSortOrder);

		List<DeliveryFeeRule> rules = baseMapper.selectList(wrapper);

		// 转换为VO
		return rules.stream().map(this::buildRuleVO).collect(Collectors.toList());
	}

	/**
	 * 校验配置是否存在
	 */
	private DeliveryFeeConfig validateConfigExists(Long configId) {
		DeliveryFeeConfig config = deliveryFeeConfigMapper.selectById(configId);
		if (config == null) {
			throw new BusinessException("CONFIG_NOT_FOUND", "配送费配置不存在");
		}
		if (config.getDeleteAt() != null) {
			throw new BusinessException("CONFIG_DELETED", "配送费配置已被删除");
		}
		return config;
	}

	/**
	 * 校验距离规则字段
	 */
	private void validateDistanceRule(DeliveryFeeRuleAddDTO dto) {
		if (dto.getDistanceStart() == null) {
			throw new BusinessException("INVALID_PARAM", "距离规则必须设置起始距离");
		}
		if (dto.getPricePerKm() == null) {
			throw new BusinessException("INVALID_PARAM", "距离规则必须设置每公里价格");
		}
		if (dto.getDistanceEnd() != null && dto.getDistanceStart().compareTo(dto.getDistanceEnd()) >= 0) {
			throw new BusinessException("INVALID_PARAM", "结束距离必须大于起始距离");
		}
	}

	/**
	 * 校验距离规则字段（用于更新）
	 */
	private void validateDistanceRuleFields(DeliveryFeeRule rule) {
		if (rule.getDistanceStart() == null) {
			throw new BusinessException("INVALID_PARAM", "距离规则必须设置起始距离");
		}
		if (rule.getPricePerKm() == null) {
			throw new BusinessException("INVALID_PARAM", "距离规则必须设置每公里价格");
		}
		if (rule.getDistanceEnd() != null && rule.getDistanceStart().compareTo(rule.getDistanceEnd()) >= 0) {
			throw new BusinessException("INVALID_PARAM", "结束距离必须大于起始距离");
		}
	}

	/**
	 * 校验时间规则字段
	 */
	private void validateTimeRule(DeliveryFeeRuleAddDTO dto) {
		if (dto.getTimeStart() == null || dto.getTimeEnd() == null) {
			throw new BusinessException("INVALID_PARAM", "时间规则必须设置开始时间和结束时间");
		}
		if (dto.getTimeFee() == null) {
			throw new BusinessException("INVALID_PARAM", "时间规则必须设置时段附加费");
		}
	}

	/**
	 * 校验时间规则字段（用于更新）
	 */
	private void validateTimeRuleFields(DeliveryFeeRule rule) {
		if (rule.getTimeStart() == null || rule.getTimeEnd() == null) {
			throw new BusinessException("INVALID_PARAM", "时间规则必须设置开始时间和结束时间");
		}
		if (rule.getTimeFee() == null) {
			throw new BusinessException("INVALID_PARAM", "时间规则必须设置时段附加费");
		}
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
	private LambdaQueryWrapper<DeliveryFeeRule> buildRuleWrapper(DeliveryFeeRuleQueryDTO queryDTO) {
		LambdaQueryWrapper<DeliveryFeeRule> wrapper = Wrappers.lambdaQuery();

		// 软删除过滤
		wrapper.isNull(DeliveryFeeRule::getDeleteAt);

		// 配置ID
		if (queryDTO.getConfigId() != null) {
			wrapper.eq(DeliveryFeeRule::getConfigId, queryDTO.getConfigId());
		}

		// 规则类型
		if (queryDTO.getRuleType() != null) {
			wrapper.eq(DeliveryFeeRule::getRuleType, queryDTO.getRuleType());
		}

		// 时段类型
		if (queryDTO.getTimeType() != null) {
			wrapper.eq(DeliveryFeeRule::getTimeType, queryDTO.getTimeType());
		}

		// 排序
		String sortField = StrUtil.isNotBlank(queryDTO.getSortField()) ? queryDTO.getSortField() : "sortOrder";
		String sortDir = StrUtil.isNotBlank(queryDTO.getSortOrder()) ? queryDTO.getSortOrder() : "asc";
		boolean isAsc = "asc".equalsIgnoreCase(sortDir);

		// 根据排序字段动态构建排序条件
		switch (sortField) {
			case "sortOrder":
				wrapper.orderBy(true, isAsc, DeliveryFeeRule::getSortOrder);
				break;
			case "ruleType":
				wrapper.orderBy(true, isAsc, DeliveryFeeRule::getRuleType);
				break;
			case "createAt":
				wrapper.orderBy(true, isAsc, DeliveryFeeRule::getCreateAt);
				break;
			case "updateAt":
				wrapper.orderBy(true, isAsc, DeliveryFeeRule::getUpdateAt);
				break;
			default:
				wrapper.orderBy(true, isAsc, DeliveryFeeRule::getSortOrder);
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

	/**
	 * 校验规则类型一致性（混合校验）
	 * 同一配置下不能同时存在距离规则和时间规则
	 */
	private void validateRuleTypeConsistency(Long configId, Integer ruleType) {
		LambdaQueryWrapper<DeliveryFeeRule> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(DeliveryFeeRule::getConfigId, configId);
		wrapper.isNull(DeliveryFeeRule::getDeleteAt);
		wrapper.ne(DeliveryFeeRule::getRuleType, ruleType);
		Long count = baseMapper.selectCount(wrapper);

		if (count > 0) {
			String existingType = ruleType == 1 ? "时间" : "距离";
			String newType = ruleType == 1 ? "距离" : "时间";
			throw new BusinessException("RULE_TYPE_CONFLICT",
					"配置已存在" + existingType + "规则，不能添加" + newType + "规则");
		}
	}

	/**
	 * 校验距离规则是否重叠
	 *
	 * @param configId       配置ID
	 * @param distanceStart  起始距离
	 * @param distanceEnd    结束距离
	 * @param excludeRuleId 排除的规则ID（更新时使用）
	 */
	private void validateDistanceRuleOverlap(Long configId, BigDecimal distanceStart, BigDecimal distanceEnd,
											 Integer excludeRuleId) {
		LambdaQueryWrapper<DeliveryFeeRule> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(DeliveryFeeRule::getConfigId, configId);
		wrapper.eq(DeliveryFeeRule::getRuleType, 1);
		wrapper.isNull(DeliveryFeeRule::getDeleteAt);
		if (excludeRuleId != null) {
			wrapper.ne(DeliveryFeeRule::getId, excludeRuleId);
		}
		wrapper.orderByAsc(DeliveryFeeRule::getDistanceStart);

		List<DeliveryFeeRule> existingRules = baseMapper.selectList(wrapper);

		for (DeliveryFeeRule existing : existingRules) {
			// 检查是否重叠：禁止端点接触
			// 重叠条件：newStart < existingEnd && newEnd > existingStart
			if (distanceStart.compareTo(existing.getDistanceEnd()) < 0
					&& distanceEnd.compareTo(existing.getDistanceStart()) > 0) {
				throw new BusinessException("DISTANCE_RULE_OVERLAP", String.format(
						"距离范围 %.2f-%.2f 与现有规则 %.2f-%.2f 重叠（包括端点接触）", distanceStart,
						distanceEnd, existing.getDistanceStart(), existing.getDistanceEnd()));
			}
		}
	}

	/**
	 * 校验距离规则覆盖范围是否连续
	 *
	 * @param configId 配置ID
	 */
	private void validateDistanceRuleCoverage(Long configId) {
		LambdaQueryWrapper<DeliveryFeeRule> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(DeliveryFeeRule::getConfigId, configId);
		wrapper.eq(DeliveryFeeRule::getRuleType, 1);
		wrapper.isNull(DeliveryFeeRule::getDeleteAt);
		wrapper.orderByAsc(DeliveryFeeRule::getDistanceStart);

		List<DeliveryFeeRule> rules = baseMapper.selectList(wrapper);

		if (rules.isEmpty()) {
			return;
		}

		// 检查是否从0开始
		if (rules.get(0).getDistanceStart().compareTo(BigDecimal.ZERO) > 0) {
			throw new BusinessException("DISTANCE_RULE_GAP", String.format(
					"距离规则存在空缺：0.00-%.2f 未覆盖，请添加起始距离为0的规则", rules.get(0).getDistanceStart()));
		}

		// 检查相邻规则之间是否有空缺
		for (int i = 0; i < rules.size() - 1; i++) {
			DeliveryFeeRule current = rules.get(i);
			DeliveryFeeRule next = rules.get(i + 1);

			if (current.getDistanceEnd().compareTo(next.getDistanceStart()) < 0) {
				throw new BusinessException("DISTANCE_RULE_GAP", String.format(
						"距离规则存在空缺：%.2f-%.2f 未覆盖，请补充该距离范围的规则", current.getDistanceEnd(),
						next.getDistanceStart()));
			}
		}
	}

	/**
	 * 校验时间规则是否重叠（支持跨午夜）
	 *
	 * @param configId       配置ID
	 * @param timeStart      开始时间
	 * @param timeEnd        结束时间
	 * @param excludeRuleId 排除的规则ID（更新时使用）
	 */
	private void validateTimeRuleOverlap(Long configId, LocalTime timeStart, LocalTime timeEnd,
										 Integer excludeRuleId) {
		LambdaQueryWrapper<DeliveryFeeRule> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(DeliveryFeeRule::getConfigId, configId);
		wrapper.eq(DeliveryFeeRule::getRuleType, 2);
		wrapper.isNull(DeliveryFeeRule::getDeleteAt);
		if (excludeRuleId != null) {
			wrapper.ne(DeliveryFeeRule::getId, excludeRuleId);
		}
		wrapper.orderByAsc(DeliveryFeeRule::getTimeStart);

		List<DeliveryFeeRule> existingRules = baseMapper.selectList(wrapper);

		for (DeliveryFeeRule existing : existingRules) {
			if (isTimeOverlap(timeStart, timeEnd, existing.getTimeStart(), existing.getTimeEnd())) {
				throw new BusinessException("TIME_RULE_OVERLAP", String.format(
						"时间段 %s-%s 与现有规则 %s-%s 重叠", timeStart, timeEnd, existing.getTimeStart(),
						existing.getTimeEnd()));
			}
		}
	}

	/**
	 * 判断两个时间段是否重叠（支持跨午夜）
	 *
	 * @param start1 第一个时间段开始
	 * @param end1   第一个时间段结束
	 * @param start2 第二个时间段开始
	 * @param end2   第二个时间段结束
	 * @return 是否重叠
	 */
	private boolean isTimeOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
		// 将时间转换为分钟数
		int start1Minutes = start1.toSecondOfDay() / 60;
		int end1Minutes = end1.toSecondOfDay() / 60;
		int start2Minutes = start2.toSecondOfDay() / 60;
		int end2Minutes = end2.toSecondOfDay() / 60;

		// 处理跨午夜的情况
		if (end1Minutes < start1Minutes) {
			end1Minutes += 24 * 60;
		}
		if (end2Minutes < start2Minutes) {
			end2Minutes += 24 * 60;
		}

		// 如果有跨午夜，需要标准化到同一周期
		if (start1Minutes > 12 * 60 && start2Minutes < 12 * 60) {
			start2Minutes += 24 * 60;
			end2Minutes += 24 * 60;
		}
		else if (start2Minutes > 12 * 60 && start1Minutes < 12 * 60) {
			start1Minutes += 24 * 60;
			end1Minutes += 24 * 60;
		}

		// 检查重叠
		return start1Minutes < end2Minutes && end1Minutes > start2Minutes;
	}

	/**
	 * 校验时间规则是否覆盖全天24小时
	 *
	 * @param configId 配置ID
	 */
	private void validateTimeRuleCoverage(Long configId) {
		LambdaQueryWrapper<DeliveryFeeRule> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(DeliveryFeeRule::getConfigId, configId);
		wrapper.eq(DeliveryFeeRule::getRuleType, 2);
		wrapper.isNull(DeliveryFeeRule::getDeleteAt);
		wrapper.orderByAsc(DeliveryFeeRule::getTimeStart);

		List<DeliveryFeeRule> rules = baseMapper.selectList(wrapper);

		if (rules.isEmpty()) {
			return;
		}

		// 将时间段转换为分钟区间
		List<int[]> intervals = new java.util.ArrayList<>();
		for (DeliveryFeeRule rule : rules) {
			int startMinutes = rule.getTimeStart().toSecondOfDay() / 60;
			int endMinutes = rule.getTimeEnd().toSecondOfDay() / 60;

			// 处理跨午夜
			if (endMinutes < startMinutes) {
				endMinutes += 24 * 60;
			}

			intervals.add(new int[] { startMinutes, endMinutes });
		}

		// 合并重叠或连续的时间段
		intervals.sort((a, b) -> Integer.compare(a[0], b[0]));
		List<int[]> merged = new java.util.ArrayList<>();
		for (int[] interval : intervals) {
			if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
				merged.add(interval);
			}
			else {
				merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
			}
		}

		// 检查是否覆盖全天（1440分钟）
		int totalCovered = 0;
		for (int[] interval : merged) {
			totalCovered += interval[1] - interval[0];
		}

		if (totalCovered < 24 * 60) {
			throw new BusinessException("TIME_RULE_NOT_COVER_ALL",
					"时间规则未覆盖全天24小时，当前覆盖 " + totalCovered + " 分钟");
		}
	}

}