package com.example.finance.vo;

import com.example.finance.common.util.PageResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户分页查询结果VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "账户分页查询结果VO")
public class PaymentAccountPageVO extends PageResult<PaymentAccountVO> {

    public PaymentAccountPageVO() {
        super();
    }

    public PaymentAccountPageVO(long current, long pageSize, long total, java.util.List<PaymentAccountVO> list) {
        super(current, pageSize, total, list);
    }
}

