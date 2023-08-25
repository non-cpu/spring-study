package com.example.springstudy.service;

import com.example.springstudy.domain.RoleType;
import com.example.springstudy.domain.User;
import com.example.springstudy.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void insertUser(User user) {
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseGet(User::new);
    }
}
