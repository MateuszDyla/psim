package com.beereal.beerealbackend.JSONresponse;

import java.time.LocalTime;

public class RankingResponse {

    private int id;
    private String username;
    private int visitedBarsNumber;
    private LocalTime gameplayTime;

    public RankingResponse(int id, String username, int visitedBarsNumber, LocalTime gameplayTime) {
        this.id = id;
        this.username = username;
        this.visitedBarsNumber = visitedBarsNumber;
        this.gameplayTime = gameplayTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVisitedBarsNumber() {
        return visitedBarsNumber;
    }

    public void setVisitedBarsNumber(int visitedBarsNumber) {
        this.visitedBarsNumber = visitedBarsNumber;
    }

    public LocalTime getGameplayTime() {
        return gameplayTime;
    }

    public void setGameplayTime(LocalTime gameplayTime) {
        this.gameplayTime = gameplayTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
