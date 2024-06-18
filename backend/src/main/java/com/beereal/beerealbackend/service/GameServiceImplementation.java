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

    @Override
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game findActiveUserGame(int userId) {
        return gameRepository.findGameByUserId(userId);
    }

    @Override
    public void deleteUserGame(int userId) {
        Game game = gameRepository.findGameByUserId(userId);
        gameRepository.delete(game);
    }

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
