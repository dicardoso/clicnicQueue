package com.example.clicnicQueue.controller;

import com.example.clicnicQueue.model.Counter;
import com.example.clicnicQueue.service.AbstractService;
import com.example.clicnicQueue.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/counter")
public class CounterController {

    private final CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping
    public ResponseEntity<List<Counter>> getAllCounters() {
        return ResponseEntity.ok(counterService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Counter> getCounterById(@PathVariable Long id) {
        Optional<Counter> counter = counterService.findById(id);
        return counter.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Counter> createCounter(@RequestBody Counter counter) {
        return ResponseEntity.ok(counterService.save(counter));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Counter> updateCounter(@PathVariable Long id, @RequestBody Counter counter) {
        if (counterService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        counter.setId(id);
        return ResponseEntity.ok(counterService.save(counter));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCounter(@PathVariable Long id) {
        if (counterService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        counterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}