package com.example.finance.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.finance.api.dto.FinanceWithdrawalApplyDTO;
import com.example.finance.api.dto.FinanceWithdrawalAuditDTO;
import com.example.finance.api.dto.FinanceWithdrawalQueryDTO;
import com.example.finance.api.entity.FinanceWithdrawal;
import com.example.finance.api.vo.FinanceWithdrawalVO;
import com.example.finance.biz.mapper.FinanceWithdrawalMapper;
import com.example.finance.biz.service.FinanceWithdrawalService;
import org.springframework.stereotype.Service;

/**
* @author 22417
* @description 针对表【finance_withdrawal(提现申请表)】的数据库操作Service实现
* @createDate 2026-01-30 11:38:51
*/
@Service
public class FinanceWithdrawalServiceImpl extends ServiceImpl<FinanceWithdrawalMapper, FinanceWithdrawal>
    implements FinanceWithdrawalService{

    @Override
    public Long applyWithdrawal(FinanceWithdrawalApplyDTO applyDTO) {
        // TODO: 实现申请提现逻辑
        return null;
    }

    @Override
    public void auditWithdrawal(FinanceWithdrawalAuditDTO auditDTO) {
        // TODO: 实现审核提现逻辑
    }

    @Override
    public Page<FinanceWithdrawalVO> listByQuery(FinanceWithdrawalQueryDTO queryDTO) {
        // TODO: 实现按条件查询提现记录逻辑
        return null;
    }

    @Override
    public FinanceWithdrawalVO getDetail(Long id) {
        // TODO: 实现获取提现记录详情逻辑
        return null;
    }
}




