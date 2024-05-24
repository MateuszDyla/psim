package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register (@RequestBody User user) {
        userService.registerUser(user);
        return "Student added";
    }
}
