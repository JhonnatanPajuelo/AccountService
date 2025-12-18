package com.example.accountservice.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record DepositCommand(
        UUID userId,
        BigDecimal amount
) {
    public DepositCommand {
        if (userId == null) throw new IllegalArgumentException("User ID no puede ser nulo");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
    }
}