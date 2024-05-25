package com.beereal.beerealbackend.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentDTO {

    private int userID;
    private int barID;
    private LocalDateTime date;
    private String text;

    public CommentDTO(int userID, int barID, LocalDateTime date, String text) {
        this.userID = userID;
        this.barID = barID;
        this.date = date;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
