package com.example.service.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.service.api.dto.CommissionConfigAddDTO;
import com.example.service.api.dto.CommissionConfigUpdateDTO;
import com.example.service.api.entity.CommissionConfig;
import com.example.service.api.vo.CommissionConfigVO;

import java.util.Map;

/**
 * 分佣配置服务接口
 */
public interface CommissionConfigService extends IService<CommissionConfig> {

    /**
     * 新增分佣配置
     */
    void addCommissionConfig(CommissionConfigAddDTO dto);

    /**
     * 更新分佣配置
     */
    void updateCommissionConfig(Long id, CommissionConfigUpdateDTO dto);

    /**
     * 根据分类ID和配置类型获取分佣配置
     * 优先级：指定分类 > 全局默认
     *
     * @param categoryId 服务分类ID（可为null）
     * @param configType 配置类型：1-全局默认，2-服务分佣，3-商家分佣，4-合伙人分佣
     * @return 分佣配置，未找到返回null
     */
    CommissionConfig getCommissionConfig(Long categoryId, Integer configType);

    /**
     * 批量获取分佣配置（用于订单创建时获取所有需要的配置）
     * 返回：Map<configType, CommissionConfig>
     *
     * @param categoryId 服务分类ID（可为null）
     * @return 分佣配置映射，key为配置类型
     */
    Map<Integer, CommissionConfig> getCommissionConfigs(Long categoryId);
}