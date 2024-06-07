package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.JSONresponse.BarDetailsResponse;
import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Visit;
import com.beereal.beerealbackend.repository.BarRepository;
import com.beereal.beerealbackend.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImplementation implements VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private BarRepository barRepository;

    @Override
    public Visit addVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public List<Bar> getUnlockedBarsByUserId(int userId) {
        ArrayList<Bar> bars = new ArrayList<>();
        List<Visit> visits = visitRepository.findByUserIdOrderByDate(userId);
        for (int i = 0; i < visits.size(); i++) {
            bars.add(visits.get(i).getBar());
        }
        List<Bar> result = new ArrayList<>(new HashSet<>(bars));
        return result;
    }

    @Override
    public int countVisitsByUser(int barId, int userId) {

        return visitRepository.findByBarIdAndUserId(barId, userId).size();
    }

    @Override
    public int countVisitsInBar(int barId) {
        return visitRepository.countVisitByBarId(barId);
    }


    @Override
    public BarDetailsResponse getBarVisitedByDetails(int barId, int userId) {
        int visitsByUser = countVisitsByUser(barId, userId);
        int visits = countVisitsInBar(barId);
        LocalDateTime lastVisit = getLastVisitDate(barId, userId);
        Optional<Bar> optionalBar = barRepository.findById(barId);
        if (!optionalBar.isPresent())
            return null;
        Bar bar = optionalBar.get();

        BarDetailsResponse barDetailsResponse = new BarDetailsResponse(bar, visits, visitsByUser, lastVisit);
        return barDetailsResponse;
    }

    @Override
    public LocalDateTime getLastVisitDate(int barId, int userId) {
        return visitRepository.findFirstByBarIdAndUserIdOrderByDateDesc(userId, barId).getDate();
    }


}