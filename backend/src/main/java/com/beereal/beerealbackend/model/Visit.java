package com.beereal.beerealbackend.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

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
    private Timestamp date;


    public Visit(int id, User user, Bar bar, Timestamp date) {
        this.id = id;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
