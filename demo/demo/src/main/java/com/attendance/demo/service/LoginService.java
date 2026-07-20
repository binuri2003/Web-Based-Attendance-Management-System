package com.attendance.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.demo.entity.User;
import com.attendance.demo.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password) {

        Optional<User> user =
                userRepository.findByUsernameAndPassword(username, password);

        return user.orElse(null);

    }

}