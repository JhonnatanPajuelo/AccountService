package com.example.accountservice.infrastructure.adapters.out.persistance.mapper;

import com.example.accountservice.domain.model.Transaction;
import com.example.accountservice.infrastructure.adapters.out.persistance.entity.TransactionEntity;

public class TransactionEntityMapper {
    public static TransactionEntity toEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .transactionId(transaction.transactionID())
                .userId(transaction.userID())
                .typeTransaction(transaction.type())
                .amount(transaction.amount())
                .createdAt(transaction.createAt())
                .build();
    }

    public static Transaction toTransaction(TransactionEntity transactionEntity) {
        return new Transaction(
                transactionEntity.getTransactionId(),
                transactionEntity.getUserId(),
                transactionEntity.getTypeTransaction(),
                transactionEntity.getAmount(),
                transactionEntity.getCreatedAt()
        );
    }
}
