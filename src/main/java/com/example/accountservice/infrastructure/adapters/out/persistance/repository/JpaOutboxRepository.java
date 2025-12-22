package com.example.accountservice.infrastructure.adapters.out.persistance.repository;

import com.example.accountservice.infrastructure.adapters.out.persistance.entity.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaOutboxRepository extends JpaRepository<OutboxEventEntity, UUID> {


    List<OutboxEventEntity> findAllByProcessedFalse();
}
