package com.example.accountservice.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record MoneyWithdrawalEvent(
        UUID eventId,
        UUID transactionId,
        UUID userId,
        BigDecimal amount,
        Instant occurredAt
) implements DomainEvent {
    @Override
    public String eventType() {
        return "MONEY_WITHDRAWN";
    }

    @Override
    public String aggregateType() {
        return "ACCOUNT";
    }
}