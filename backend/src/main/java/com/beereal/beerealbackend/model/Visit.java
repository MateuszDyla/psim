package com.beereal.beerealbackend.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(nullable = false, name = "user_id")
    @ManyToOne
    private User user;
    @JoinColumn(nullable = false, name="bar_id")
    @ManyToOne
    private Bar bar;
    @Column(nullable = false)
    private LocalDateTime date;


    public Visit(User user, Bar bar, LocalDateTime date) {
        this.user = user;
        this.bar = bar;
        this.date = date;
    }

    public Visit() {

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
}
