package com.example.merchant.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigAddDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigBatchAddDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigQueryDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigStatusDTO;
import com.example.merchant.api.dto.delivery.DeliveryFeeConfigUpdateDTO;
import com.example.merchant.api.entity.DeliveryFeeConfig;
import com.example.merchant.api.vo.DeliveryFeeConfigVO;

/**
 * 配送费配置服务
 *
 * @author campus-merchant
 */
public interface DeliveryFeeConfigService extends IService<DeliveryFeeConfig> {

	/**
	 * 分页查询配送费配置列表
	 *
	 * @param queryDTO 查询条件
	 * @return 配置分页结果（包含关联的规则列表）
	 */
	PageResult<DeliveryFeeConfigVO> listConfigs(DeliveryFeeConfigQueryDTO queryDTO);

	/**
	 * 查询配送费配置详情
	 *
	 * @param id 配置ID
	 * @return 配置详情（包含关联的规则列表）
	 */
	DeliveryFeeConfigVO getConfigDetail(Long id);

	/**
	 * 新增配送费配置
	 *
	 * @param dto 新增DTO
	 */
	void addConfig(DeliveryFeeConfigAddDTO dto);

	/**
	 * 更新配送费配置
	 *
	 * @param id  配置ID
	 * @param dto 更新DTO
	 */
	void updateConfig(Long id, DeliveryFeeConfigUpdateDTO dto);

	/**
	 * 删除配送费配置
	 *
	 * @param id 配置ID
	 */
	void deleteConfig(Long id);

	/**
	 * 批量新增配送费配置
	 *
	 * @param dto 批量新增DTO
	 */
	void batchAddConfigs(DeliveryFeeConfigBatchAddDTO dto);

	/**
	 * 修改配置状态
	 *
	 * @param id        配置ID
	 * @param statusDTO 状态变更DTO
	 */
	void updateConfigStatus(Long id, DeliveryFeeConfigStatusDTO statusDTO);

}