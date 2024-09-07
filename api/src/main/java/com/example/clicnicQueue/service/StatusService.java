package com.example.clicnicQueue.service;

import com.example.clicnicQueue.model.Status;
import com.example.clicnicQueue.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService extends AbstractService<Status, Long> {

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        super(statusRepository);
    }
}
