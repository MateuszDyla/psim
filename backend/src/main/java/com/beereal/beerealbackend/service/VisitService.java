package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Visit;

import java.util.List;

public interface VisitService {
    Visit addVisit(Visit visit);
    List<Bar> getUnlockedBarsByUserId(int userId);

    int countVisitsByUser(int barId, int userId);
}