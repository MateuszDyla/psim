package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Game;
import org.springframework.stereotype.Service;

@Service
public interface GameService {

    Game addGame(Game game);

    Game findActiveUserGame(int userId);

    void deleteUserGame(int userId);

    Game updateGame(int userId, int barId, long minutesToAdd);
}
