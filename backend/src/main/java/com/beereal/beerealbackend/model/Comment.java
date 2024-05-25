package com.beereal.beerealbackend.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(nullable = false, name= "bar_id")
    private Bar bar;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private String text;

    public Comment(User user, Bar bar, LocalDateTime date, String text) {
        this.user = user;
        this.bar = bar;
        this.date = date;
        this.text = text;
    }

    public Comment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
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
