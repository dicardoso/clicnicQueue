package com.example.clicnicQueue.service;

import com.example.clicnicQueue.model.TicketEvent;
import com.example.clicnicQueue.repository.TicketEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketEventService extends AbstractService<TicketEvent, Long> {

    @Autowired
    public TicketEventService(TicketEventRepository ticketEventRepository) {
        super(ticketEventRepository);
    }
}
