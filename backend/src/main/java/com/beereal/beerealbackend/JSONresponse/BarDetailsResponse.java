package com.beereal.beerealbackend.JSONresponse;

import com.beereal.beerealbackend.model.Bar;

import java.time.LocalDateTime;

public class BarDetailsResponse {
    Bar bar;
    int allVisits;
    int visitsByUser;
    LocalDateTime lastVisit;

    public BarDetailsResponse(Bar bar, int allVisits, int visitsByUser) {
        this.bar = bar;
        this.allVisits = allVisits;
        this.visitsByUser = visitsByUser;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public int getAllVisits() {
        return allVisits;
    }

    public void setAllVisits(int allVisits) {
        this.allVisits = allVisits;
    }

    public int getVisitsByUser() {
        return visitsByUser;
    }

    public void setVisitsByUser(int visitsByUser) {
        this.visitsByUser = visitsByUser;
    }

    public LocalDateTime     getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }
}
