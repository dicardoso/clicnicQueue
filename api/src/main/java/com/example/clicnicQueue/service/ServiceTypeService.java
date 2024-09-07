package com.example.clicnicQueue.service;

import com.example.clicnicQueue.model.ServiceType;
import com.example.clicnicQueue.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTypeService extends AbstractService<ServiceType, Long> {

    @Autowired
    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository) {
        super(serviceTypeRepository);
    }
}
