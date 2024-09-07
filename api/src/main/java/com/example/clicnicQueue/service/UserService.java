package com.example.clicnicQueue.service;

import com.example.clicnicQueue.model.User;
import com.example.clicnicQueue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, Long> {

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }
}
