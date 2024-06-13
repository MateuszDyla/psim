package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Game;
import org.springframework.stereotype.Service;

@Service
public interface GameService {

    public Game addGame(Game game);

    public Game findActiveUserGame(int userId);

    public void deleteUserGame(int userId);
}
