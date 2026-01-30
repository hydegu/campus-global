package com.example.finance.api.vo;

import com.example.finance.api.enums.TransactionTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 流水详情VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "FinanceTransactionVO", description = "流水详情VO")
public class FinanceTransactionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "流水ID", example = "1")
    private Long id;

    @Schema(description = "流水号", example = "FT20240130123456789")
    private String transactionNo;

    @Schema(description = "用户ID", example = "1001")
    private Long userId;

    @Schema(description = "用户姓名", example = "张三")
    private String userName;

    @Schema(description = "用户电话", example = "13800138000")
    private String userPhone;

    @Schema(description = "交易类型：1-打款 2-消费 3-退款 4-提现", example = "2")
    private Integer transactionType;

    @Schema(description = "交易类型名称", example = "消费")
    private String transactionTypeName;

    @Schema(description = "交易金额（正数为收入，负数为支出）", example = "100.00")
    private BigDecimal amount;

    @Schema(description = "关联业务类型：1-订单 2-提现 3-退款 4-打款", example = "1")
    private Integer relatedType;

    @Schema(description = "关联业务ID", example = "1001")
    private Long relatedId;

    @Schema(description = "备注", example = "订单支付")
    private String remark;

    @Schema(description = "创建时间", example = "2024-01-30 12:00:00")
    private LocalDateTime createAt;
}