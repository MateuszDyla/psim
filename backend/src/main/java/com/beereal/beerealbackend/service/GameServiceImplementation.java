package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Game;
import com.beereal.beerealbackend.repository.BarRepository;
import com.beereal.beerealbackend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GameServiceImplementation implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private BarRepository barRepository;

    /**
     * Dodaje obiekt Game do bazy danych
     * @param game gra do dodania
     * @return dodany obiekt Game
     */
    @Override
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    /**
     * Każdy użytkownik może mieć aktywną (zarejestrowaną w BD) grę, usługa ta pobiera informacje o aktualnej grze
     * @param userId id użytkownika
     * @return obiekt Game będący informacją o aktualnej grze danego użytkownika
     */
    @Override
    public Game findActiveUserGame(int userId) {
        return gameRepository.findGameByUserId(userId);
    }

    /**
     * Każdy użytkownik może mieć aktywną (zarejestrowaną w BD) grę. Po zakończeniu gry, powiązany z nią rekord jest usuwany.
     * @param userId id użytkownika, którego gra jest usuwana
     */
    @Override
    public void deleteUserGame(int userId) {
        Game game = gameRepository.findGameByUserId(userId);
        gameRepository.delete(game);
    }

    /***
     * Aktualizuje stan gru aktualnego użytkownika - dodaje minuty po wizycie w barze i inkrementuje licznik odwiedzonych barów
     * @param userId id użytkownika
     * @param barId id baru
     * @param minutesToAdd minuty do inkrementacji po wizycie w barze
     * @return
     */
    @Override
    public Game updateGame(int userId, int barId, long minutesToAdd) {
        Game game = findActiveUserGame(userId);
        Optional<Bar> bar = barRepository.findById(barId);
        game.setCurrentBar(bar.get());
        game.setVisitedBars(game.getVisitedBars()+1);
        LocalDateTime newTime = game.getFinishUntil().plusMinutes(minutesToAdd);
        game.setFinishUntil(newTime);
        return gameRepository.save(game);
    }
}
