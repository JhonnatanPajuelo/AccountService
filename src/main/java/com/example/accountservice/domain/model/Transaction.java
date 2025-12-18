package com.example.accountservice.domain.model;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Transaction {
    private UUID transactionID;
    private User userID;
    private String type;  //DEPOSIT o WITHDRAWAL
    private BigDecimal amount;
    private Instant createAt;

}

