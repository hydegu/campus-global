package com.example.finance.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.finance.api.dto.FinanceTransactionAddDTO;
import com.example.finance.api.dto.FinanceTransactionQueryDTO;
import com.example.finance.api.entity.FinanceTransaction;
import com.example.finance.api.vo.FinanceTransactionVO;

/**
* @author 22417
* @description 针对表【finance_transaction(财务流水表)】的数据库操作Service
* @createDate 2026-01-30 11:38:51
*/
public interface FinanceTransactionService extends IService<FinanceTransaction> {

    /**
     * 创建流水
     *
     * @param addDTO 创建流水请求
     * @return 流水ID
     */
    Long createTransaction(FinanceTransactionAddDTO addDTO);

    /**
     * 按用户查询流水
     *
     * @param userId 用户ID
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 流水分页列表
     */
    Page<FinanceTransactionVO> listByUser(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 按条件查询流水
     *
     * @param queryDTO 查询条件
     * @return 流水分页列表
     */
    Page<FinanceTransactionVO> listByQuery(FinanceTransactionQueryDTO queryDTO);

    /**
     * 获取流水详情
     *
     * @param id 流水ID
     * @return 流水详情VO
     */
    FinanceTransactionVO getDetail(Long id);
}
