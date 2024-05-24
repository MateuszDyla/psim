package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.RankingEntry;
import com.beereal.beerealbackend.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingServiceImplementation implements RankingService{

    @Autowired
    private RankingRepository rankingRepository;

    @Override
    public List<RankingEntry> getRanking() {
        return rankingRepository.findAll();
    }

    @Override
    public RankingEntry addEntry(RankingEntry rankingEntry) {
        return rankingRepository.save(rankingEntry);
    }
}
