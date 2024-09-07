package com.example.clicnicQueue.repository;

import com.example.clicnicQueue.model.TicketEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketEventRepository extends JpaRepository<TicketEvent, Long> {
}
