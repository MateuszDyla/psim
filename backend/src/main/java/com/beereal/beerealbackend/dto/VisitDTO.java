package com.beereal.beerealbackend.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class VisitDTO {

    private int userID;
    private int barID;
    private LocalDateTime date;

    public VisitDTO(int userID, int barID, LocalDateTime date) {
        this.userID = userID;
        this.barID = barID;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
