package com.beereal.beerealbackend.controller;


import com.beereal.beerealbackend.dto.GameDTO;
import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Game;
import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.service.BarService;
import com.beereal.beerealbackend.service.GameService;
import com.beereal.beerealbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private UserService userService;
    @Autowired
    private BarService barService;
    @Autowired
    private GameService gameService;

    long addedMinutes = 15;

    @PostMapping()
    public ResponseEntity<String> addEntry(@RequestBody GameDTO gameDTO) {
       User user =  userService.getUserByID(gameDTO.getUserID());
        if (user == null)
            return ResponseEntity.badRequest().body("User not found");
        Bar bar = barService.getBarByID(gameDTO.getBarID());
        if (bar == null)
            return ResponseEntity.badRequest().body("Bar not found");
        Game game = new Game(user, bar, gameDTO.getElapsedTime());
        Game savedGame = gameService.addGame(game);
        if (savedGame == null)
            return ResponseEntity.badRequest().body("Error");

        return ResponseEntity.ok("Game created");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Game> showActiveUserGame(@PathVariable Integer userId) {
        return ResponseEntity.ok(gameService.findActiveUserGame(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserGame(@PathVariable int userId){
        gameService.deleteUserGame(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Game> updateGame(@PathVariable int userId, @RequestParam int barId) {

        Game updatedgame = gameService.updateGame(userId, barId, addedMinutes);
        if (updatedgame == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(updatedgame);
    }

}
