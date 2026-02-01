package com.example.service.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.service.api.dto.CommissionConfigAddDTO;
import com.example.service.api.dto.CommissionConfigUpdateDTO;
import com.example.service.api.entity.CommissionConfig;
import com.example.service.api.vo.CommissionConfigVO;

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
}