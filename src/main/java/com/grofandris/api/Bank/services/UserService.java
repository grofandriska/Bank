package com.grofandris.api.Bank.services;

import com.grofandris.api.Bank.models.User;
import com.grofandris.api.Bank.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
