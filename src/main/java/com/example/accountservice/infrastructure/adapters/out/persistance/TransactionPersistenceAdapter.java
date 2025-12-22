package com.example.accountservice.infrastructure.adapters.out.persistance;

import com.example.accountservice.application.port.out.TransactionRepositoryPort;
import com.example.accountservice.domain.model.Transaction;
import com.example.accountservice.infrastructure.adapters.out.persistance.entity.TransactionEntity;
import com.example.accountservice.infrastructure.adapters.out.persistance.mapper.TransactionEntityMapper;
import com.example.accountservice.infrastructure.adapters.out.persistance.repository.SpringDataTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Optional;
import java.util.UUID;

@Component
public class TransactionPersistenceAdapter implements TransactionRepositoryPort {

    private final SpringDataTransactionRepository springDataTransactionRepository;

    @Autowired
    private TransactionPersistenceAdapter(SpringDataTransactionRepository springDataTransactionRepository) {
        this.springDataTransactionRepository = springDataTransactionRepository;
    }
    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionSaved = springDataTransactionRepository.save
                (TransactionEntityMapper.toEntity(transaction));
        return TransactionEntityMapper.toTransaction(transactionSaved);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return springDataTransactionRepository.findById(id).map(TransactionEntityMapper::toTransaction);
    }
}
