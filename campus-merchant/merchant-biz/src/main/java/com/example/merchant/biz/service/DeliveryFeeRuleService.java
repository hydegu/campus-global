package com.example.merchant.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleAddDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleQueryDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleStatusDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeRuleUpdateDTO;
import com.example.merchant.api.entity.DeliveryFeeRule;
import com.example.merchant.api.vo.DeliveryFeeRuleVO;

import java.util.List;

/**
 * 配送费规则服务
 *
 * @author campus-merchant
 */
public interface DeliveryFeeRuleService extends IService<DeliveryFeeRule> {

	/**
	 * 分页查询配送费规则列表
	 *
	 * @param queryDTO 查询条件
	 * @return 规则分页结果
	 */
	PageResult<DeliveryFeeRuleVO> listRules(DeliveryFeeRuleQueryDTO queryDTO);

	/**
	 * 查询配送费规则详情
	 *
	 * @param id 规则ID
	 * @return 规则详情
	 */
	DeliveryFeeRuleVO getRuleDetail(Integer id);

	/**
	 * 新增配送费规则
	 *
	 * @param dto 新增DTO
	 */
	void addRule(DeliveryFeeRuleAddDTO dto);

	/**
	 * 更新配送费规则
	 *
	 * @param id  规则ID
	 * @param dto 更新DTO
	 */
	void updateRule(Integer id, DeliveryFeeRuleUpdateDTO dto);

	/**
	 * 删除配送费规则
	 *
	 * @param id 规则ID
	 */
	void deleteRule(Integer id);

	/**
	 * 修改规则状态
	 *
	 * @param id        规则ID
	 * @param statusDTO 状态变更DTO
	 */
	void updateRuleStatus(Integer id, DeliveryFeeRuleStatusDTO statusDTO);

	/**
	 * 根据配置ID查询规则列表
	 *
	 * @param configId 配置ID
	 * @return 规则列表
	 */
	List<DeliveryFeeRuleVO> listRulesByConfigId(Long configId);

}