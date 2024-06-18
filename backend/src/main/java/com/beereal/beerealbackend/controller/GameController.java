package com.beereal.beerealbackend.controller;


import com.beereal.beerealbackend.dto.GameDTO;
import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Game;
import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.service.BarService;
import com.beereal.beerealbackend.service.GameService;
import com.beereal.beerealbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/game")
public class GameController {
    private final UserService userService;
    private final BarService barService;
    private final GameService gameService;

    public GameController(UserService userService, BarService barService, GameService gameService) {
        this.userService = userService;
        this.barService = barService;
        this.gameService = gameService;
    }

    long addedMinutes = 15;

    /***
     * Dodawanie gry do bazy danych gier
     * Wywoływane na starcie gry - jeśli uczestnik nie ma rozpoczętej gry
     * @param gameDTO
     * @return status dodania gry do bazy danych
     */
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

    /***
     * Wyświetla informacje o trwającej grze danego użytkownika
     * @param userId id użytkownika
     * @return Obiekt game będący informacjami o aktualnej grze podanego użytkownika/błąd
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Game> showActiveUserGame(@PathVariable Integer userId) {
        Game game = gameService.findActiveUserGame(userId);
        if (game != null)
            return ResponseEntity.ok(gameService.findActiveUserGame(userId));
        else return ResponseEntity.badRequest().build();
    }

    /***
     * Usuwa grę danego użytkownika
     * Wywołane po zakończeniu gry przez naciśnięcie przycisku lub upłynięcie czasu
     * @param userId id użytkownika którego gra dotyczy
     * @return status usunięcia
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserGame(@PathVariable int userId){
        gameService.deleteUserGame(userId);
        return ResponseEntity.ok().build();
    }

    /***
     * Aktualizuje aktualną grę użytkownika - zmienia bar na nowy (podany w requeście), inkrementuje pozostały zcas o wartość stałej "addMinutes", oraz inkrementuje licznik odwiedzonych barów
     * Wywoływane po poprawnym zeskanowaniu kodu qr - zatwierdzeniu wizyty w barze
     * @param userId id użytkownika którego gra dotyczy
     * @param barId bar id
     * @return Obiekt game z uwzględnionymi aktualizajcami
     */
    @PutMapping("/{userId}")
    public ResponseEntity<Game> updateGame(@PathVariable int userId, @RequestParam int barId) {

        Game updatedgame = gameService.updateGame(userId, barId, addedMinutes);
        if (updatedgame == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(updatedgame);
    }

}
