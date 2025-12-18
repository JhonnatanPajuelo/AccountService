package com.example.accountservice.application.mapper;

import com.example.accountservice.domain.model.MoneyDepositedEvent;
import com.example.accountservice.domain.model.Transaction;

import java.util.UUID;

public class TransactionMapper {
    public static MoneyDepositedEvent toEvent(Transaction transaction) {
        return new MoneyDepositedEvent(
                UUID.randomUUID(),
                transaction.transactionID(),
                transaction.userID(),
                transaction.amount(),
                transaction.createAt()
        );
    }
}
