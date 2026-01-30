package com.example.finance.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.finance.api.dto.PaymentRecordCreateDTO;
import com.example.finance.api.dto.PaymentRecordQueryDTO;
import com.example.finance.api.entity.PaymentRecord;
import com.example.finance.api.vo.PaymentRecordVO;

/**
* @author 22417
* @description 针对表【payment_record(打款记录表)】的数据库操作Service
* @createDate 2026-01-30 11:38:51
*/
public interface PaymentRecordService extends IService<PaymentRecord> {

    /**
     * 创建打款记录
     *
     * @param createDTO 创建打款记录请求
     * @return 打款记录ID
     */
    Long createPaymentRecord(PaymentRecordCreateDTO createDTO);

    /**
     * 执行打款
     *
     * @param paymentId 打款记录ID
     */
    void executePayment(Long paymentId);

    /**
     * 按条件查询打款记录
     *
     * @param queryDTO 查询条件
     * @return 打款记录分页列表
     */
    Page<PaymentRecordVO> listByQuery(PaymentRecordQueryDTO queryDTO);

    /**
     * 获取打款记录详情
     *
     * @param id 打款记录ID
     * @return 打款记录详情VO
     */
    PaymentRecordVO getDetail(Long id);
}
