package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.JSONresponse.RankingResponse;
import com.beereal.beerealbackend.dto.RankingEntryDTO;
import com.beereal.beerealbackend.model.RankingEntry;
import com.beereal.beerealbackend.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankingServiceImplementation implements RankingService{

    @Autowired
    private RankingRepository rankingRepository;

    @Override
    public List<RankingResponse> getRanking() {
        List<RankingEntry> rankingEntries = rankingRepository.findAll();
        ArrayList<RankingResponse> responses = new ArrayList<>();
        for (int i = 0; i < rankingEntries.size(); i++) {
            RankingEntry entry = rankingEntries.get(i);
            RankingResponse response = new RankingResponse(entry.getId(), entry.getUser().getUsername(), entry.getVisited_bars_number(), entry.getGameplayTime());
            responses.add(response);
        }
        return responses;

    }

    @Override
    public RankingEntry addEntry(RankingEntry rankingEntry) {
        return rankingRepository.save(rankingEntry);
    }
}
