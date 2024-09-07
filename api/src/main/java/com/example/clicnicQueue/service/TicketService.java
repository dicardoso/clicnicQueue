package com.example.clicnicQueue.service;

import com.example.clicnicQueue.model.Ticket;
import com.example.clicnicQueue.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends AbstractService<Ticket, Long> {

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        super(ticketRepository);
    }
}
