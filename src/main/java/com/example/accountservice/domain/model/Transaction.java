package com.example.accountservice.domain.model;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Transaction(UUID transactionID,
                          UUID userID,
                          TransactionType type,
                          BigDecimal amount,
                          Instant createAt){}
