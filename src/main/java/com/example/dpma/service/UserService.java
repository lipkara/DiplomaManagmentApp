package com.example.dpma.service;

import com.example.dpma.model.User;

import org.springframework.stereotype.Service;

@Service
public interface    UserService {
    public void saveUser(User user);

    public boolean isUserPresent(User user);

    public User loadUserByName(String name);
}