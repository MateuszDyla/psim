package com.beereal.beerealbackend.dto;

import java.sql.Timestamp;

public class VisitDTO {

    private int userID;
    private int barID;
    private Timestamp date;

    public VisitDTO(int userID, int barID, Timestamp date) {
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
