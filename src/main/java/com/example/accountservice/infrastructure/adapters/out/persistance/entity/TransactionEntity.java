package com.example.accountservice.infrastructure.adapters.out.persistance.entity;

import com.example.accountservice.domain.model.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
public class TransactionEntity {
    @Id
    private UUID transactionId;

    @Column(nullable = false)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType typeTransaction;

    @Column(nullable = false, precision = 19, scale = 4)// Precisión financiera
    private BigDecimal amount;

    @Column(nullable = false,updatable = false)
    private Instant createdAt;
}
