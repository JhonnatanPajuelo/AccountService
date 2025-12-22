package com.example.accountservice.infrastructure.adapters.out.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_event")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OutboxEventEntity {
    @Id
    private UUID eventId;
    private String aggregateType; // ej: "Account"
    private String eventType;     // ej: "MoneyDeposited"
    private String payload;       // El evento convertido a JSON string
    private Instant createdAt;
    private boolean processed;    // Para saber si ya se envió
}