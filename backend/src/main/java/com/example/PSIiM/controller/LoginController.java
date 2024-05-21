package com.example.PSIiM.controller;
import com.example.PSIiM.model.Gamer;
import com.example.PSIiM.repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private GamerRepository gamerRepository;

    @PostMapping("/api/login")
    public Gamer loginUser(@RequestBody Gamer gamer) {
        // Tutaj możesz wywołać metodę zapisującą gracza do bazy danych
        return gamerRepository.save(gamer);
    }
}
