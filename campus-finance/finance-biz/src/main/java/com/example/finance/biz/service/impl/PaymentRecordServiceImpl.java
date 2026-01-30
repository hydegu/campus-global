package com.example.finance.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.finance.api.dto.PaymentRecordCreateDTO;
import com.example.finance.api.dto.PaymentRecordQueryDTO;
import com.example.finance.api.entity.PaymentRecord;
import com.example.finance.api.vo.PaymentRecordVO;
import com.example.finance.biz.mapper.PaymentRecordMapper;
import com.example.finance.biz.service.PaymentRecordService;
import org.springframework.stereotype.Service;

/**
* @author 22417
* @description 针对表【payment_record(打款记录表)】的数据库操作Service实现
* @createDate 2026-01-30 11:38:51
*/
@Service
public class PaymentRecordServiceImpl extends ServiceImpl<PaymentRecordMapper, PaymentRecord>
    implements PaymentRecordService{

    @Override
    public Long createPaymentRecord(PaymentRecordCreateDTO createDTO) {
        // TODO: 实现创建打款记录逻辑
        return null;
    }

    @Override
    public void executePayment(Long paymentId) {
        // TODO: 实现执行打款逻辑
    }

    @Override
    public Page<PaymentRecordVO> listByQuery(PaymentRecordQueryDTO queryDTO) {
        // TODO: 实现按条件查询打款记录逻辑
        return null;
    }

    @Override
    public PaymentRecordVO getDetail(Long id) {
        // TODO: 实现获取打款记录详情逻辑
        return null;
    }
}




