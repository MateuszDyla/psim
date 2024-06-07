package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.JSONresponse.BarDetailsResponse;
import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Visit;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface VisitService {
    Visit addVisit(Visit visit);
    List<Bar> getUnlockedBarsByUserId(int userId);
    int countVisitsByUser(int barId, int userId);
    int countVisitsInBar(int barId);
    BarDetailsResponse getBarVisitedByDetails(int barId, int userId);

    LocalDateTime getLastVisitDate(int barId, int userId);
}