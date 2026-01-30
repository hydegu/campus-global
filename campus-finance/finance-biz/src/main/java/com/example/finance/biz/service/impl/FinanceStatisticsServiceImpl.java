package com.example.finance.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.finance.api.dto.CategoryTurnoverQueryDTO;
import com.example.finance.api.dto.MerchantTurnoverQueryDTO;
import com.example.finance.api.dto.StatisticsQueryDTO;
import com.example.finance.api.dto.StaffTurnoverQueryDTO;
import com.example.finance.api.enums.RelatedTypeEnum;
import com.example.finance.api.enums.TransactionTypeEnum;
import com.example.finance.api.pojo.MonthlyTurnoverItem;
import com.example.finance.api.vo.CategoryTurnoverVO;
import com.example.finance.api.vo.MerchantTurnoverVO;
import com.example.finance.api.vo.StaffTurnoverVO;
import com.example.finance.api.vo.TransactionLogVO;
import com.example.finance.biz.mapper.FinanceTransactionMapper;
import com.example.finance.biz.service.FinanceStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 财务统计服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FinanceStatisticsServiceImpl implements FinanceStatisticsService {

    private final FinanceTransactionMapper financeTransactionMapper;

    @Override
    public TransactionLogVO getOverview(StatisticsQueryDTO queryDTO) {
        TransactionLogVO vo = new TransactionLogVO();

        // 1. 获取日期范围（默认今天）
        LocalDate today = LocalDate.now();
        LocalDate startDate = queryDTO.getStartDate() != null ? queryDTO.getStartDate() : today;
        LocalDate endDate = queryDTO.getEndDate() != null ? queryDTO.getEndDate() : today;
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        // 2. 统计今日打款金额
        BigDecimal todayPaymentAmount = financeTransactionMapper.sumAmountByTypeAndTime(
                TransactionTypeEnum.PAYMENT.getCode(), startDateTime, endDateTime);
        vo.setTodayPaymentAmount(todayPaymentAmount != null ? todayPaymentAmount : BigDecimal.ZERO);

        // 3. 统计今日提现金额
        BigDecimal todayWithdrawalAmount = financeTransactionMapper.sumAmountByTypeAndTime(
                TransactionTypeEnum.WITHDRAWAL.getCode(), startDateTime, endDateTime);
        vo.setTodayWithdrawalAmount(todayWithdrawalAmount != null ? todayWithdrawalAmount : BigDecimal.ZERO);

        // 4. 统计今日总流水（消费+打款）
        BigDecimal todayTurnover = financeTransactionMapper.sumAmountByTypeAndTime(
                TransactionTypeEnum.CONSUMPTION.getCode(), startDateTime, endDateTime);
        if (todayPaymentAmount != null) {
            todayTurnover = todayTurnover.add(todayPaymentAmount);
        }
        vo.setTodayTurnover(todayTurnover != null ? todayTurnover : BigDecimal.ZERO);

        // 5. 统计今日成交金额
        BigDecimal todayTransactionAmount = financeTransactionMapper.sumAmountByTypeAndTime(
                TransactionTypeEnum.CONSUMPTION.getCode(), startDateTime, endDateTime);
        vo.setTodayTransactionAmount(todayTransactionAmount != null ? todayTransactionAmount : BigDecimal.ZERO);

        // 6. 统计今日服务收益（TODO：根据业务规则计算）
        vo.setTodayServiceRevenue(BigDecimal.ZERO);

        // 7. 统计今日成交订单数
        Integer todayOrderCount = financeTransactionMapper.countByRelatedTypeAndTime(
                RelatedTypeEnum.ORDER.getCode(), startDateTime, endDateTime);
        vo.setTodayOrderCount(todayOrderCount != null ? todayOrderCount : 0);

        // 8. 查询最近12个月的月度流水
        List<MonthlyTurnoverItem> monthlyData = financeTransactionMapper.queryMonthlyTurnover(12);
        vo.setMonthlyData(monthlyData);

        return vo;
    }

    @Override
    public Page<MerchantTurnoverVO> getMerchantTurnover(MerchantTurnoverQueryDTO queryDTO) {
        // 1. 构建查询条件
        LocalDateTime startTime = queryDTO.getStartTime() != null ?
                queryDTO.getStartTime() : LocalDate.now().minusMonths(1).atStartOfDay();
        LocalDateTime endTime = queryDTO.getEndTime() != null ?
                queryDTO.getEndTime() : LocalDateTime.now();

        // 2. 查询商家流水统计（需要关联订单表、商家表）
        // TODO: 实现复杂的统计查询，可能需要自定义SQL或多次查询

        // 3. 组装VO列表
        List<MerchantTurnoverVO> voList = new ArrayList<>();

        // 4. 分页返回
        Page<MerchantTurnoverVO> result = new Page<>();
        result.setCurrent(queryDTO.getPageNum());
        result.setSize(queryDTO.getPageSize());
        result.setRecords(voList);

        log.info("商家流水统计查询，时间范围：{} - {}，商户ID：{}，品类ID：{}",
                startTime, endTime, queryDTO.getMerchantId(), queryDTO.getCategoryId());

        return result;
    }

    @Override
    public Page<CategoryTurnoverVO> getCategoryTurnover(CategoryTurnoverQueryDTO queryDTO) {
        // 1. 构建查询条件
        LocalDateTime startTime = queryDTO.getStartTime() != null ?
                queryDTO.getStartTime() : LocalDate.now().minusMonths(1).atStartOfDay();
        LocalDateTime endTime = queryDTO.getEndTime() != null ?
                queryDTO.getEndTime() : LocalDateTime.now();

        // 2. 查询品类流水统计（需要关联订单商品表、分类表）
        // TODO: 实现复杂的统计查询，可能需要自定义SQL或多次查询

        // 3. 组装VO列表
        List<CategoryTurnoverVO> voList = new ArrayList<>();

        // 4. 分页返回
        Page<CategoryTurnoverVO> result = new Page<>();
        result.setCurrent(queryDTO.getPageNum());
        result.setSize(queryDTO.getPageSize());
        result.setRecords(voList);

        log.info("品类流水统计查询，时间范围：{} - {}，品类ID：{}",
                startTime, endTime, queryDTO.getCategoryId());

        return result;
    }

    @Override
    public Page<StaffTurnoverVO> getStaffTurnover(StaffTurnoverQueryDTO queryDTO) {
        // 1. 构建查询条件
        LocalDateTime startTime = queryDTO.getStartTime() != null ?
                queryDTO.getStartTime() : LocalDate.now().minusMonths(1).atStartOfDay();
        LocalDateTime endTime = queryDTO.getEndTime() != null ?
                queryDTO.getEndTime() : LocalDateTime.now();

        // 2. 查询服务人员流水统计（需要关联跑腿订单表、用户表）
        // TODO: 实现复杂的统计查询，可能需要自定义SQL或多次查询

        // 3. 组装VO列表
        List<StaffTurnoverVO> voList = new ArrayList<>();

        // 4. 分页返回
        Page<StaffTurnoverVO> result = new Page<>();
        result.setCurrent(queryDTO.getPageNum());
        result.setSize(queryDTO.getPageSize());
        result.setRecords(voList);

        log.info("服务人员流水统计查询，时间范围：{} - {}，服务人员ID：{}",
                startTime, endTime, queryDTO.getStaffId());

        return result;
    }
}