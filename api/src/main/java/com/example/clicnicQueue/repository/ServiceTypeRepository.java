package com.example.clicnicQueue.repository;

import com.example.clicnicQueue.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}
