package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Game;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public interface GameService {

    public Game addGame(Game game);

    public Game findActiveUserGame(int userId);

    public void deleteUserGame(int userId);

    public Game updateGame(int userId, int barId, long minutesToAdd);
}
