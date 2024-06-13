package com.beereal.beerealbackend.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name="current_bar_id")
    private Bar currentBar;

    @Column(nullable = false, name="elapsed_time")
    private LocalTime elapsedTime;

    public Game() {
    }

    public Game(User user, Bar lastBar, LocalTime elapsedTime) {
        this.user = user;
        this.currentBar = lastBar;
        this.elapsedTime = elapsedTime;
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

    public Bar getCurrentBar() {
        return currentBar;
    }

    public void setCurrentBar(Bar currentBar) {
        this.currentBar = currentBar;
    }

    public LocalTime getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(LocalTime elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
