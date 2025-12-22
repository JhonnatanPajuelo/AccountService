package com.example.accountservice.infrastructure.adapters.out.messaging;

import com.example.accountservice.application.port.out.EventPublisherPort;
import com.example.accountservice.domain.model.DomainEvent;
import com.example.accountservice.domain.model.MoneyDepositedEvent;
import com.example.accountservice.infrastructure.adapters.out.persistance.entity.OutboxEventEntity;
import com.example.accountservice.infrastructure.adapters.out.persistance.repository.JpaOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
public class TransactionalOutboxAdapter implements EventPublisherPort {

    private final JpaOutboxRepository outboxRepository;
    private final ObjectMapper objectMapper; // Jackson para convertir a String

    public TransactionalOutboxAdapter (JpaOutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public void publish(DomainEvent event) {
        try {
            OutboxEventEntity outboxEventEntity = OutboxEventEntity.builder()
                    .eventId(UUID.randomUUID())
                    .aggregateType(event.aggregateType())
                    .eventType(event.eventType())
                    .payload(objectMapper.writeValueAsString(event))
                    .createdAt(Instant.now())
                    .processed(false)
                    .build();
            outboxRepository.save(outboxEventEntity);
        }catch (Exception e) {
            throw new RuntimeException("Error al serializar evento para Outbox", e);
        }
    }
}
