package com.example.accountservice.application.service;

import com.example.accountservice.application.mapper.TransactionMapper;
import com.example.accountservice.application.port.in.DepositUseCase;
import com.example.accountservice.application.port.out.EventPublisherPort;
import com.example.accountservice.application.port.out.TransactionRepositoryPort;
import com.example.accountservice.domain.model.DepositCommand;
import com.example.accountservice.domain.model.MoneyDepositedEvent;
import com.example.accountservice.domain.model.Transaction;
import com.example.accountservice.domain.model.TransactionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
@Slf4j
@Service
public class AccountDepositService implements DepositUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final EventPublisherPort eventPublisherPort;

    @Autowired
    public AccountDepositService(TransactionRepositoryPort transactionRepository, EventPublisherPort eventPublisher) {
        this.transactionRepositoryPort = transactionRepository;
        this.eventPublisherPort = eventPublisher;
    }


    @Override
    @Transactional
    public UUID execute(DepositCommand command) {
        log.info("Inicio proceso depósito - Usuario: {}, Monto: {}", command.userId(), command.amount());
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                command.userId(),
                TransactionType.DEPOSIT,
                command.amount(),
                Instant.now()
        );
        Transaction savedTransaction = transactionRepositoryPort.save(transaction);
        // 3. Crear el Evento (Hecho ocurrido) para los demás microservicios
        MoneyDepositedEvent event = TransactionMapper.toEvent(savedTransaction);

        // 4. Publicar el evento
        // Esto viajará a Reporting Service para actualizar el saldo
        eventPublisherPort.publish(event);
        log.info("Depósito completado exitosamente. Transacción: {}", savedTransaction.transactionID());
        // 5. Retornar el ID al Controller
        return savedTransaction.transactionID();
    }
}
