package com.beereal.beerealbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Table(name="ranking")
public class RankingEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(nullable = false, name="user_id")
    @ManyToOne
    private User user;
    @Column(nullable = false, name="visited_bars_number")
    private int visitedBarsNumber;
    @Column(nullable = false, name="gameplay_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime gameplayTime;

    public RankingEntry(User user, int visitedBarsNumber, LocalTime gameplayTime) {
        this.user = user;
        this.visitedBarsNumber = visitedBarsNumber;
        this.gameplayTime = gameplayTime;
    }

    public RankingEntry() {

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

    public int getVisited_bars_number() {
        return visitedBarsNumber;
    }

    public void setVisited_bars_number(int visited_bars_number) {
        this.visitedBarsNumber = visited_bars_number;
    }

    public LocalTime getGameplayTime() {
        return gameplayTime;
    }

    public void setGameplayTime(LocalTime gameplayTime) {
        this.gameplayTime = gameplayTime;
    }
}
