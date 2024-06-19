package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.JSONresponse.RankingResponse;
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

    /**
     * Zwraca listę z wszystkimi wpisami w rankingu
     * @return Lista wszystkich wpisów w rankingu
     */
    @Override
    public List<RankingResponse> getRanking() {
        List<RankingEntry> rankingEntries = rankingRepository.findAll();
        ArrayList<RankingResponse> responses = new ArrayList<>();
        for (RankingEntry entry : rankingEntries) {
            RankingResponse response = new RankingResponse(entry.getId(), entry.getUser().getUsername(), entry.getVisited_bars_number(), entry.getGameplayTime());
            responses.add(response);
        }
        return responses;

    }

    /**
     * Dodaje informacje z obiektu RankingEntry do bazy danych
     * @param rankingEntry dodawnay obiekt
     * @return dodawany obiekt
     */
    @Override
    public RankingEntry addEntry(RankingEntry rankingEntry) {
        return rankingRepository.save(rankingEntry);
    }
}
