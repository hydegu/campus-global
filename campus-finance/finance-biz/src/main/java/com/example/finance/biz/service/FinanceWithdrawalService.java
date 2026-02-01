package com.example.finance.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.finance.api.dto.FinanceWithdrawalApplyDTO;
import com.example.finance.api.dto.FinanceWithdrawalAuditDTO;
import com.example.finance.api.dto.FinanceWithdrawalQueryDTO;
import com.example.finance.api.entity.FinanceWithdrawal;
import com.example.finance.api.vo.FinanceWithdrawalVO;

/**
* @author 22417
* @description 针对表【finance_withdrawal(提现申请表)】的数据库操作Service
* @createDate 2026-01-30 11:38:51
*/
public interface FinanceWithdrawalService extends IService<FinanceWithdrawal> {

    /**
     * 申请提现
     *
     * @param applyDTO 提现申请请求
     * @return 提现记录ID
     */
    Long applyWithdrawal(FinanceWithdrawalApplyDTO applyDTO);



    /**
     * 按条件查询提现记录
     *
     * @param queryDTO 查询条件
     * @return 提现记录分页列表
     */
    Page<FinanceWithdrawalVO> listByQuery(FinanceWithdrawalQueryDTO queryDTO);

    /**
     * 获取提现记录详情
     *
     * @param id 提现记录ID
     * @return 提现记录详情VO
     */
    FinanceWithdrawalVO getDetail(Long id);
}
