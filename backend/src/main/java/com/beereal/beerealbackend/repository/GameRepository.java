package com.beereal.beerealbackend.repository;

import com.beereal.beerealbackend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findGameByUserId(int userId);
}
