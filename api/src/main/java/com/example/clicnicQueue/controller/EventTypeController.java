package com.example.clicnicQueue.controller;

import com.example.clicnicQueue.model.EventType;
import com.example.clicnicQueue.service.EventTypeService;
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
@RequestMapping("/api/event-type")
public class EventTypeController {

    private final EventTypeService eventTypeService;

    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping
    public ResponseEntity<List<EventType>> getAllStatus() {
        return ResponseEntity.ok(eventTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventType> getStatusById(@PathVariable Long id) {
        Optional<EventType> eventType = eventTypeService.findById(id);
        return eventType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}