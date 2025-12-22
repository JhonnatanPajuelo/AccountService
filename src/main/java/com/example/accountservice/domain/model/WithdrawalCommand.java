package com.example.accountservice.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record WithdrawalCommand(
        UUID userId,
        BigDecimal amount
) {
}
