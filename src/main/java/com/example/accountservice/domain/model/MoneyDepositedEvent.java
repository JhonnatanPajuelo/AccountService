package com.example.accountservice.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record MoneyDepositedEvent(
        UUID eventId,
        UUID transactionId,
        UUID userId,
        BigDecimal amount,
        Instant occurredAt
) {
}