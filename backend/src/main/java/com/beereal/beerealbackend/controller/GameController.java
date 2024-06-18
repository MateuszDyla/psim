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

import java.time.LocalDateTime;
import java.time.ZoneId;

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
    public ResponseEntity<Game> addEntry(@RequestBody GameDTO gameDTO) {
       User user =  userService.getUserByID(gameDTO.getUserID());
        if (user == null)
            return ResponseEntity.badRequest().build();
        Bar bar = barService.getBarByID(gameDTO.getBarID());
        if (bar == null)
            return ResponseEntity.badRequest().build();

        LocalDateTime finishTime = LocalDateTime.now(ZoneId.of("Europe/Paris")).plusMinutes(2 * addedMinutes);
        if(gameDTO.getFinishUntil() != null)
            finishTime = gameDTO.getFinishUntil();
        Game game = new Game(user, bar, finishTime, 0);
        Game savedGame = gameService.addGame(game);
        if (savedGame == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(game);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Game> showActiveUserGame(@PathVariable Integer userId) {
        Game game = gameService.findActiveUserGame(userId);
        if (game != null)
            return ResponseEntity.ok(gameService.findActiveUserGame(userId));
        else return ResponseEntity.badRequest().build();
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
