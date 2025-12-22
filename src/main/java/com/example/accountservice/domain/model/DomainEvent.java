package com.example.accountservice.domain.model;

import java.util.UUID;

public interface DomainEvent {
    String eventType();
    String aggregateType();
}