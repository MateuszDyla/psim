package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User registerUser(User user);
    public User getUserByID(int id);
}
