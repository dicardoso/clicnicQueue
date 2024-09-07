package com.example.clicnicQueue.repository;

import com.example.clicnicQueue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
