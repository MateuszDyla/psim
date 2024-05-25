package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Visit;
import com.beereal.beerealbackend.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class VisitServiceImplementation implements VisitService {
    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Visit addVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public List<Bar> getUnlockedBarsByUserId(int userId) {
        ArrayList<Bar> bars = new ArrayList<>();
        List<Visit> visits = visitRepository.findByUserId(userId);
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

}