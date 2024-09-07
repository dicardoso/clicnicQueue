package com.example.clicnicQueue.service;

import com.example.clicnicQueue.model.EventType;
import com.example.clicnicQueue.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTypeService extends AbstractService<EventType, Long> {

    @Autowired
    public EventTypeService(EventTypeRepository eventTypeRepository) {
        super(eventTypeRepository);
    }
}
