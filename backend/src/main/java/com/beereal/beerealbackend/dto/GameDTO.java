package com.beereal.beerealbackend.dto;

import java.time.LocalTime;

public class GameDTO {

    private int userID;
    private int barID;
    private LocalTime elapsedTime;

    public GameDTO(int userID, int barID, LocalTime elapsedTime) {
        this.userID = userID;
        this.barID = barID;
        this.elapsedTime = elapsedTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBarID() {
        return barID;
    }

    public void setBarID(int barID) {
        this.barID = barID;
    }

    public LocalTime getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(LocalTime elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
