package com.example.accountservice.infrastructure.adapters.in.rest.controllers;

import com.example.accountservice.application.port.in.DepositUseCase;
import com.example.accountservice.domain.model.DepositCommand;
import com.example.accountservice.infrastructure.adapters.in.rest.dto.request.DepositRequest;
import com.example.accountservice.infrastructure.adapters.in.rest.dto.response.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class DepositController {
    private final DepositUseCase depositUseCase;

    @Autowired
    public DepositController(DepositUseCase depositUseCase) {
        this.depositUseCase = depositUseCase;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody DepositRequest request) {

        // 1. Mapeamos el DTO de infraestructura al Command de Dominio
        DepositCommand command = new DepositCommand(
                request.userId(),
                request.amount()
        );

        // 2. Ejecutamos el caso de uso
        UUID transactionId = depositUseCase.execute(command);

        // 3. Respondemos con un 202 Accepted (porque es un sistema asíncrono)
        return ResponseEntity.accepted()
                .body(TransactionResponse.of(transactionId, "Depósito en proceso"));
    }
}
