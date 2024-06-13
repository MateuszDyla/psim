package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Game;
import com.beereal.beerealbackend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class GameServiceImplementation implements GameService {
    @Autowired
    private GameRepository gameRepository;

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
    public void updateTimeAndBar(Game game, Bar bar, LocalTime time) {

    }
}
