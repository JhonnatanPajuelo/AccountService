package com.example.accountservice.infrastructure.adapters.in.rest.dto.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.UUID;

public record DepositRequest(
        @NotNull
        UUID userId,

        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.01", message = "El monto mínimo debe ser 0.01")
        BigDecimal amount
) {
    // Constructor compacto para validación extra de seguridad
    public DepositRequest {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
    }
}