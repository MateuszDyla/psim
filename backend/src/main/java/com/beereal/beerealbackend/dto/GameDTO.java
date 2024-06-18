package com.beereal.beerealbackend.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class GameDTO {

    private int userID;
    private int barID;
    private LocalDateTime finishUntil;
    private LocalDateTime startDate;


    public GameDTO(int userID, int barID, LocalDateTime finishUntil, LocalDateTime startDate) {
        this.userID = userID;
        this.barID = barID;
        this.finishUntil = finishUntil;
        this.startDate = startDate;
    }

    public GameDTO(int userID, int barID) {
        this.userID = userID;
        this.barID = barID;
    }

    public GameDTO() {
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

    public LocalDateTime getFinishUntil() {
        return finishUntil;
    }

    public void setFinishUntil(LocalDateTime finishUntil) {
        this.finishUntil = finishUntil;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}
