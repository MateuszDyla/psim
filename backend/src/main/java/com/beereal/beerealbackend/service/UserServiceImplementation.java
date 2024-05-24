package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByID(int id) {
        Optional<User> userOptional =  userRepository.findById(id);
        return userOptional.orElse(null);
    }
}
