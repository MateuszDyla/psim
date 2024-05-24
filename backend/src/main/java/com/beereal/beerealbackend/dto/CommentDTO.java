package com.beereal.beerealbackend.dto;

import java.sql.Timestamp;

public class CommentDTO {

    private int userID;
    private int barID;
    private Timestamp date;
    private String text;

    public CommentDTO(int userID, int barID, Timestamp date, String text) {
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
