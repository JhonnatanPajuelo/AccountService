package com.example.accountservice.application.port.in;

import com.example.accountservice.domain.model.DepositCommand;

import java.util.UUID;

public interface DepositUseCase {
    UUID execute(DepositCommand command);
}
