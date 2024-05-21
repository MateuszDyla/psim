package com.example.PSIiM.controller;
import com.example.PSIiM.model.Gamer;
import com.example.PSIiM.service.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gamer")
public class GamerController {
    @Autowired
    private GamerService gamerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerGamer(@RequestBody Gamer gamer) {
        gamerService.registerGamer(gamer);
        return ResponseEntity.ok("Gracz został zarejestrowany");
    }

    @PostMapping("/login")
    public ResponseEntity<Gamer> loginGamer(@RequestParam String login, @RequestParam String password) {
        Gamer gamer = gamerService.loginGamer(login, password);
        if (gamer != null) {
            return ResponseEntity.ok(gamer);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<Gamer>> getRanking() {
        List<Gamer> ranking = gamerService.getRanking();
        return ResponseEntity.ok(ranking);
    }

    @GetMapping("/visitedBars")
    public ResponseEntity<List<String>> getVisitedBars(@RequestParam int userId) {
        List<String> visitedBars = gamerService.getVisitedBars(userId);
        return ResponseEntity.ok(visitedBars);
    }

    @PostMapping("/visitBar")
    public ResponseEntity<Void> visitBar(@RequestParam int userId, @RequestParam int barId) {
        gamerService.visitBar(userId, barId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/endGame")
    public ResponseEntity<Void> endGame(@RequestParam int userId) {
        gamerService.endGame(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<String>> getAllComments(@RequestParam int barId) {
        List<String> comments = gamerService.getAllComments(barId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/addComment")
    public ResponseEntity<Void> addComment(@RequestParam int barId, @RequestBody String comment) {
        gamerService.addComment(barId, comment);
        return ResponseEntity.ok().build();
    }

    // Dodane funkcje zgodnie z wymaganiami
    @GetMapping("/visitedBarsDetails")
    public ResponseEntity<List<String>> getVisitedBarsDetails(@RequestParam int userId) {
        List<String> visitedBarsDetails = gamerService.getVisitedBarsDetails(userId);
        return ResponseEntity.ok(visitedBarsDetails);
    }

    @GetMapping("/barDetails")
    public ResponseEntity<List<String>> getBarDetails(@RequestParam int barId) {
        List<String> barDetails = gamerService.getBarDetails(barId);
        return ResponseEntity.ok(barDetails);
    }
}