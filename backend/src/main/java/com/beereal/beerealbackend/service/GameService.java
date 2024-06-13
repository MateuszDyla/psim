package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Game;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public interface GameService {

    public Game addGame(Game game);

    public Game findActiveUserGame(int userId);

    public void deleteUserGame(int userId);

    public void updateTimeAndBar(Game game, Bar bar, LocalTime time);
}
