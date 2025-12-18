package com.example.accountservice.domain.model;

import java.time.Instant;
import java.util.UUID;

public class User {
    private UUID userID;
    private String userName;
    private Instant createAt;
}
