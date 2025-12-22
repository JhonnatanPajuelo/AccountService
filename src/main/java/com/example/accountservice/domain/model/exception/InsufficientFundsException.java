package com.example.accountservice.domain.model.exception;

import java.util.UUID;

public class InsufficientFundsException extends BusinessException {
    public InsufficientFundsException(UUID userId) {
        super("El usuario " + userId + " no tiene saldo suficiente para esta operación.");
    }
}