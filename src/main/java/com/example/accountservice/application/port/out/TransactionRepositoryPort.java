package com.example.accountservice.application.port.out;

import com.example.accountservice.domain.model.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);

    // Podríamos necesitar esto para el futuro (validaciones)
    Optional<Transaction> findById(UUID id);
}
