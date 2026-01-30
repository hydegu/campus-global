package com.example.finance.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.finance.api.dto.FinanceTransactionAddDTO;
import com.example.finance.api.dto.FinanceTransactionQueryDTO;
import com.example.finance.api.entity.FinanceTransaction;
import com.example.finance.api.vo.FinanceTransactionVO;
import com.example.finance.biz.mapper.FinanceTransactionMapper;
import com.example.finance.biz.service.FinanceTransactionService;
import org.springframework.stereotype.Service;

/**
* @author 22417
* @description 针对表【finance_transaction(财务流水表)】的数据库操作Service实现
* @createDate 2026-01-30 11:38:51
*/
@Service
public class FinanceTransactionServiceImpl extends ServiceImpl<FinanceTransactionMapper, FinanceTransaction>
    implements FinanceTransactionService{

    @Override
    public Long createTransaction(FinanceTransactionAddDTO addDTO) {
        // TODO: 实现创建流水逻辑
        return null;
    }

    @Override
    public Page<FinanceTransactionVO> listByUser(Long userId, Integer pageNum, Integer pageSize) {
        // TODO: 实现按用户查询流水逻辑
        return null;
    }

    @Override
    public Page<FinanceTransactionVO> listByQuery(FinanceTransactionQueryDTO queryDTO) {
        // TODO: 实现按条件查询流水逻辑
        return null;
    }

    @Override
    public FinanceTransactionVO getDetail(Long id) {
        // TODO: 实现获取流水详情逻辑
        return null;
    }
}




