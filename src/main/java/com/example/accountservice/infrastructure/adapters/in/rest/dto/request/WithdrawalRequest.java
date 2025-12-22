package com.example.accountservice.infrastructure.adapters.in.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record WithdrawalRequest(
        @NotNull
         UUID userId,
        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "1.00", message = "El monto mínimo debe ser 1.00")
        BigDecimal amount
        ){}
