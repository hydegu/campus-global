package com.example.finance.biz.mapper;

import com.example.finance.api.entity.FinanceTransaction;
import com.example.finance.api.pojo.MonthlyTurnoverItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 22417
 * @description 针对表【finance_transaction(财务流水表)】的数据库操作Mapper
 * @createDate 2026-01-30 11:38:51
 * @Entity com.example.finance.api.entity.FinanceTransaction
 */
@Mapper
public interface FinanceTransactionMapper extends BaseMapper<FinanceTransaction> {

    /**
     * 根据交易类型和时间范围统计金额总和
     *
     * @param transactionType 交易类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 金额总和
     */
    BigDecimal sumAmountByTypeAndTime(@Param("transactionType") Integer transactionType,
                                      @Param("startTime") LocalDateTime startTime,
                                      @Param("endTime") LocalDateTime endTime);

    /**
     * 根据关联业务类型和时间范围统计记录数
     *
     * @param relatedType 关联业务类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 记录数
     */
    Integer countByRelatedTypeAndTime(@Param("relatedType") Integer relatedType,
                                       @Param("startTime") LocalDateTime startTime,
                                       @Param("endTime") LocalDateTime endTime);

    /**
     * 查询最近N个月的月度流水数据
     *
     * @param months 月数
     * @return 月度流水列表
     */
    List<MonthlyTurnoverItem> queryMonthlyTurnover(@Param("months") Integer months);
}