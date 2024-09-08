package com.example.clicnicQueue.repository;

import com.example.clicnicQueue.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findTopByServiceTypeIdOrderByIssuedAtDesc(Long serviceTypeId);

}
