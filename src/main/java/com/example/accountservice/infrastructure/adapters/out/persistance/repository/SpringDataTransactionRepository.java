package com.example.accountservice.infrastructure.adapters.out.persistance.repository;

import com.example.accountservice.infrastructure.adapters.out.persistance.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, UUID> {
}
