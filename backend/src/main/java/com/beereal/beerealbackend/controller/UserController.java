package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.service.UserService;
import com.beereal.beerealbackend.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VisitService visitService;



    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @PostMapping("/register")
    public String register (@RequestBody User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userService.registerUser(user);
        return "Student added";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
      if (userService.authenticateUser(username, password))
            return ResponseEntity.ok("User authenticated");
      else return ResponseEntity.status(401).body("Not authenticated");
    }

    @GetMapping("/{userId}/unlocked")
    public ResponseEntity<List<Bar>> getUnlockedBarsByUserId(@PathVariable int userId) {
        List<Bar> bars = visitService.getUnlockedBarsByUserId(userId);
        return ResponseEntity.ok(bars);
    }
}