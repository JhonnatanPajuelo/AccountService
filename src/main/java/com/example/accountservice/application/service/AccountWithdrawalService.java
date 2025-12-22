package com.example.accountservice.application.service;

import com.example.accountservice.application.mapper.TransactionMapper;
import com.example.accountservice.application.port.in.WithdrawalUseCase;
import com.example.accountservice.application.port.out.TransactionRepositoryPort;
import com.example.accountservice.domain.model.MoneyWithdrawalEvent;
import com.example.accountservice.domain.model.Transaction;
import com.example.accountservice.domain.model.WithdrawalCommand;
import com.example.accountservice.infrastructure.adapters.out.messaging.TransactionalOutboxAdapter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AccountWithdrawalService implements WithdrawalUseCase {
    private final TransactionRepositoryPort transactionRepository;
    private final TransactionalOutboxAdapter outboxAdapter;

    public AccountWithdrawalService(TransactionRepositoryPort transactionRepository, TransactionalOutboxAdapter outboxAdapter) {
        this.transactionRepository = transactionRepository;
        this.outboxAdapter = outboxAdapter;
    }

    @Override
    @Transactional
    public UUID execute(WithdrawalCommand command) {
        log.info("Inicio proceso retiro - Usuario: {}, Monto: {}", command.userId(), command.amount());
        Transaction savedTransaction=transactionRepository.save(TransactionMapper.toTransactionWithdraw(command));
        MoneyWithdrawalEvent event = TransactionMapper.toEventWithdrawal(savedTransaction);
        log.info("Publicando evento de retiro en RabbitMQ: {}", event.eventId());
        outboxAdapter.publish(event);
        log.info("Retiro completado exitosamente. Transacción: {}", savedTransaction.transactionID());
        return savedTransaction.transactionID();
    }
}