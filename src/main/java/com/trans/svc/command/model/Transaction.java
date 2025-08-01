package com.trans.svc.command.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions", indexes = {
        @Index(name = "idx_txn_account_eventdate", columnList = "account_id,eventDate"),
        @Index(name = "uix_txn_idem", columnList = "idempotencyKey", unique = true)
})
@Getter
@Setter
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private OperationType operationType;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount; // always positive

    private BigDecimal signedAmount; // derived

    private String currency = "INR";

    private LocalDateTime eventDate = LocalDateTime.now();

    @Column(unique = true)
    private String idempotencyKey;
}
