package com.example.clicnicQueue.controller;

import com.example.clicnicQueue.dto.ticket.TicketRequestDTO;
import com.example.clicnicQueue.dto.ticket.TicketResponseDTO;
import com.example.clicnicQueue.model.Ticket;
import com.example.clicnicQueue.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllStatus() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getStatusById(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.findById(id);
        return ticket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PostMapping("/generate")
    public ResponseEntity<TicketResponseDTO> generateTicket(@RequestBody TicketRequestDTO data) {
        TicketResponseDTO ticket = ticketService.generateTicket(data.getServiceTypeId());
        return ResponseEntity.ok(ticket);
    }
}