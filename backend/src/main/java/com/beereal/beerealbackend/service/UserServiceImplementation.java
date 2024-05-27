package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User registerUser(User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return userRepository.save(user);
    }

    @Override
    public User getUserByID(int id) {
        Optional<User> userOptional =  userRepository.findById(id);
        return userOptional.orElse(null);
    }
    @Override
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent())
            return false;
        User user = userOptional.get();
        if (passwordEncoder.matches(password, user.getPassword()))
            return true;
        else return false;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
