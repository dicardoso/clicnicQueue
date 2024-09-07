package com.example.clicnicQueue.repository;

import com.example.clicnicQueue.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Long> {
}
