package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User registerUser(User user);
    public User getUserByID(int id);
    User authenticateUser(String username, String password);
    List<User> getAll();
}
