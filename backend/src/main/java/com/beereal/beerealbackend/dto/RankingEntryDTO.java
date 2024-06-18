package com.beereal.beerealbackend.dto;

import java.time.LocalTime;

public class RankingEntryDTO {

    private int userID;
    private int visitedBarsNumber;
    private LocalTime gameplayTime;

    public RankingEntryDTO(int userID, int visitedBarsNumber, LocalTime gameplayTime) {
        this.userID = userID;
        this.visitedBarsNumber = visitedBarsNumber;
        this.gameplayTime = gameplayTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
}
