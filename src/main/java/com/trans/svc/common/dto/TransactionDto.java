package com.trans.svc.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class TransactionDto {
    private Long accountId;
    private Integer operationTypeId;
    private BigDecimal amount;
    private String idempotencyKey;
}
