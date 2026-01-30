package com.example.finance.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.finance.api.dto.PaymentAccountAddDTO;
import com.example.finance.api.dto.PaymentAccountQueryDTO;
import com.example.finance.api.entity.PaymentAccount;
import com.example.finance.api.vo.PaymentAccountVO;
import com.example.finance.biz.mapper.PaymentAccountMapper;
import com.example.finance.biz.service.PaymentAccountService;
import org.springframework.stereotype.Service;

/**
* @author 22417
* @description 针对表【payment_account(账户表)】的数据库操作Service实现
* @createDate 2026-01-30 11:38:51
*/
@Service
public class PaymentAccountServiceImpl extends ServiceImpl<PaymentAccountMapper, PaymentAccount>
    implements PaymentAccountService{

    @Override
    public Long addAccount(PaymentAccountAddDTO addDTO) {
        // TODO: 实现添加平台账户逻辑
        return null;
    }

    @Override
    public Page<PaymentAccountVO> listByQuery(PaymentAccountQueryDTO queryDTO) {
        // TODO: 实现按条件查询账户逻辑
        return null;
    }

    @Override
    public void updateStatus(Long accountId, Integer status) {
        // TODO: 实现更新账户状态逻辑
    }
}




