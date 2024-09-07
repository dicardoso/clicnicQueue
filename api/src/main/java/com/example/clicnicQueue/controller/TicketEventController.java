package com.example.clicnicQueue.controller;

import com.example.clicnicQueue.model.Ticket;
import com.example.clicnicQueue.model.TicketEvent;
import com.example.clicnicQueue.service.TicketEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket-event")
public class TicketEventController {

    private final TicketEventService ticketEventService;

    @Autowired
    public TicketEventController(TicketEventService ticketEventService) {
        this.ticketEventService = ticketEventService;
    }

    @GetMapping
    public ResponseEntity<List<TicketEvent>> getAllStatus() {
        return ResponseEntity.ok(ticketEventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketEvent> getStatusById(@PathVariable Long id) {
        Optional<TicketEvent> ticketEvent = ticketEventService.findById(id);
        return ticketEvent.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}