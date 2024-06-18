package com.beereal.beerealbackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
    @Column(nullable = false, name="start_date")
    private LocalDateTime startDate;
    @Column(nullable = false, name="finish_until")
    private LocalDateTime finishUntil;

    @Column(nullable = false, name="visited_bars")
    int visitedBars;



    public Game() {
    }

    public Game(User user, Bar lastBar, LocalDateTime finishUntil, int visitedBars) {
        startDate = LocalDateTime.now(ZoneId.of("Europe/Paris"));
        this.user = user;
        this.currentBar = lastBar;
        this.finishUntil = finishUntil;
        this.visitedBars = visitedBars;
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

    public LocalDateTime getFinishUntil() {
        return finishUntil;
    }

    public void setFinishUntil(LocalDateTime finishUntil) {
        this.finishUntil = finishUntil;
    }

    public int getVisitedBars() {
        return visitedBars;
    }

    public void setVisitedBars(int visitedBars) {
        this.visitedBars = visitedBars;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}


