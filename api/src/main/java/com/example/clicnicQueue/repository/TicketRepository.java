package com.example.clicnicQueue.repository;

import com.example.clicnicQueue.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findTopByServiceTypeIdOrderByIssuedAtDesc(Long serviceTypeId);

    @Query("SELECT t FROM Ticket t WHERE t.status.id = :statusId ORDER BY t.serviceType.priority DESC, t.issuedAt ASC")
    List<Ticket> findTicketsByStatusOrderByPriorityAndIssuedAt(Long statusId);
}
