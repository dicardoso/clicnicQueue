package com.example.clicnicQueue.repository;

import com.example.clicnicQueue.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
