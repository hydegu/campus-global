package com.example.finance.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.finance.api.dto.CategoryTurnoverQueryDTO;
import com.example.finance.api.dto.MerchantTurnoverQueryDTO;
import com.example.finance.api.dto.StatisticsQueryDTO;
import com.example.finance.api.dto.StaffTurnoverQueryDTO;
import com.example.finance.api.vo.CategoryTurnoverVO;
import com.example.finance.api.vo.MerchantTurnoverVO;
import com.example.finance.api.vo.StaffTurnoverVO;
import com.example.finance.api.vo.TransactionLogVO;

/**
 * 财务统计服务
 */
public interface FinanceStatisticsService {

    /**
     * 获取统计页数据
     *
     * @param queryDTO 查询条件
     * @return 统计页数据
     */
    TransactionLogVO getOverview(StatisticsQueryDTO queryDTO);

    /**
     * 商家流水统计
     *
     * @param queryDTO 查询条件
     * @return 商家流水列表
     */
    Page<MerchantTurnoverVO> getMerchantTurnover(MerchantTurnoverQueryDTO queryDTO);

    /**
     * 品类流水统计
     *
     * @param queryDTO 查询条件
     * @return 品类流水列表
     */
    Page<CategoryTurnoverVO> getCategoryTurnover(CategoryTurnoverQueryDTO queryDTO);

    /**
     * 服务人员流水统计
     *
     * @param queryDTO 查询条件
     * @return 服务人员流水列表
     */
    Page<StaffTurnoverVO> getStaffTurnover(StaffTurnoverQueryDTO queryDTO);
}