package com.example.accountservice.application.port.in;

import com.example.accountservice.domain.model.WithdrawalCommand;

import java.util.UUID;

public interface WithdrawalUseCase {
    UUID execute(WithdrawalCommand command);
}
