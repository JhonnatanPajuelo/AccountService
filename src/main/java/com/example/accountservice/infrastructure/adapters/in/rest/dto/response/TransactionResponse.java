package com.example.accountservice.infrastructure.adapters.in.rest.dto.response;

import java.time.Instant;
import java.util.UUID;

public record TransactionResponse(
        UUID transactionId,
        String message,
        Instant timestamp
) {
    public static TransactionResponse of(UUID id, String msg) {
        return new TransactionResponse(id, msg, Instant.now());
    }
}