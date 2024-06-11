package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.service.UserService;
import com.beereal.beerealbackend.service.VisitService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VisitService visitService;

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userService.registerUser(user);
        return "Student added";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        if (userService.authenticateUser(username, password)) {
            String token = generateToken(username);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Not authenticated");
        }
    }

    private String generateToken(String username) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Ustal czas wygaśnięcia tokenu (np. 1 godzina)
        long expMillis = nowMillis + 3600000; // 1 godzina
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SECRET_KEY)
                .compact();
    }

    @GetMapping("/{userId}/unlocked")
    public ResponseEntity<List<Bar>> getUnlockedBarsByUserId(@PathVariable int userId) {
        List<Bar> bars = visitService.getUnlockedBarsByUserId(userId);
        return ResponseEntity.ok(bars);
    }
}