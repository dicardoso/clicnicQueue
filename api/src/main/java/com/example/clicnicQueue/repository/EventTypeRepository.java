package com.example.clicnicQueue.repository;

import com.example.clicnicQueue.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
