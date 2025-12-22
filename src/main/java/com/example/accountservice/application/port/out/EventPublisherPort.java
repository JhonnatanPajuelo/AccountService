package com.example.accountservice.application.port.out;

import com.example.accountservice.domain.model.DomainEvent;
import com.example.accountservice.domain.model.MoneyDepositedEvent;

public interface EventPublisherPort {
    void publish(DomainEvent event);
}
