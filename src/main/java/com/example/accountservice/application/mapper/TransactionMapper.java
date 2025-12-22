package com.example.accountservice.application.mapper;

import com.example.accountservice.domain.model.*;

import java.time.Instant;
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
    public static MoneyWithdrawalEvent toEventWithdrawal(Transaction transaction) {
        return new MoneyWithdrawalEvent(
                UUID.randomUUID(),
                transaction.transactionID(),
                transaction.userID(),
                transaction.amount(),
                transaction.createAt()
        );
    }

    public static Transaction toTransactionWithdraw(WithdrawalCommand command) {
        return new Transaction(
                UUID.randomUUID(),
                command.userId(),
                TransactionType.WITHDRAWAL,
                command.amount(),
                Instant.now()
        );
    }
}
