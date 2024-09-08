package com.example.clicnicQueue.repository;

import com.example.clicnicQueue.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findTopByServiceTypeIdOrderByIssuedAtDesc(Long serviceTypeId);

}
