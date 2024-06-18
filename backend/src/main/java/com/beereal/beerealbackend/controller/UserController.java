package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.service.UserService;
import com.beereal.beerealbackend.service.VisitService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final VisitService visitService;

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserService userService, VisitService visitService) {
        this.userService = userService;
        this.visitService = visitService;
    }

    /**
     * Rejestruje użytkownika
     * @param user dane użytkownika
     * @return status operacji dodania uzytkownika do bazy danych
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        User registeredUser = userService.registerUser(user);
        if (registeredUser != null)
            return ResponseEntity.status(200).body("User added");
        else return ResponseEntity.badRequest().build();
    }

    /***
     *
     * @param loginData dane logowania (username, hasło)
     * @return status logowania
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        if (userService.authenticateUser(username, password)) {
            String token = generateToken(username);
            return ok(token);
        } else {
            return ResponseEntity.status(401).body("Not authenticated");
        }
    }

    /***
     *
     * @param username nazwa uzytkownika
     * @return token użytkownika
     */
    private String generateToken(String username) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expMillis = nowMillis + 3600000;
        Date exp = new Date(expMillis);

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", user.getId())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SECRET_KEY)
                .compact();
    }

    /***
     * Podaje id użytkownika na podstawie tokenu autentykacji
     * @param token token jwt
     * @return id użytkownika
     */
    @GetMapping("/getUserId")
    public ResponseEntity<Integer> getUserId(@RequestHeader("Authorization") String token) {
        try {
            String tokenWithoutBearer = token.replace("Bearer ", "");
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(tokenWithoutBearer)
                    .getBody();
            return ok((Integer) claims.get("userId"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     *
     * @param userId id uzytkownika
     * @return Lista odwiedzonych barów przez danego użytkownika (odwiedzenie = wpis do tablicy wizyt)
     */
    @GetMapping("/{userId}/unlocked")
    public ResponseEntity<List<Bar>> getUnlockedBarsByUserId(@PathVariable int userId) {
        List<Bar> bars = visitService.getUnlockedBarsByUserId(userId);
        return ok(bars);
    }
}