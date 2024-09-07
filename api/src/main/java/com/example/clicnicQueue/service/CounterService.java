package com.example.clicnicQueue.service;

import com.example.clicnicQueue.model.Counter;
import com.example.clicnicQueue.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService extends AbstractService<Counter, Long> {

    @Autowired
    public CounterService(CounterRepository counterRepository) {
        super(counterRepository);
    }
}
