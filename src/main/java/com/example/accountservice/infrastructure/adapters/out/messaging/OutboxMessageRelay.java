package com.example.accountservice.infrastructure.adapters.out.messaging;

import com.example.accountservice.infrastructure.adapters.out.persistance.entity.OutboxEventEntity;
import com.example.accountservice.infrastructure.adapters.out.persistance.repository.JpaOutboxRepository;
import com.example.accountservice.infrastructure.config.RabbitMqConfig;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OutboxMessageRelay {
    private final JpaOutboxRepository outboxRepository;
    private final RabbitTemplate rabbitTemplate;

    private final String routingKeyDeposited="account.deposited";

    public OutboxMessageRelay(JpaOutboxRepository outboxRepository, RabbitTemplate rabbitTemplate) {
        this.outboxRepository = outboxRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000) // Cada 5 segundos
    @Transactional
    public void publishPendingEvents(){
        List<OutboxEventEntity> pendingEvents = outboxRepository.findAllByProcessedFalse();
        if (pendingEvents.isEmpty()) return;

        for(OutboxEventEntity pendingEvent : pendingEvents) {
            try {
                rabbitTemplate.convertAndSend(
                        RabbitMqConfig.EXCHANGE_NAME,
                        routingKeyDeposited,
                        pendingEvent.getPayload()
                );
                pendingEvent.setProcessed(true);
                outboxRepository.save(pendingEvent);
        }catch (Exception e) {
                log.error("Error enviando evento {} a RabbitMQ, se reintentará", pendingEvent.getEventId());
            }
        }

    }

}
