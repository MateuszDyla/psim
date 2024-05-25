package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.JSONresponse.RankingResponse;
import com.beereal.beerealbackend.model.RankingEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface RankingService {
    public List<RankingResponse> getRanking();

    public RankingEntry addEntry(RankingEntry rankingEntry);
}
