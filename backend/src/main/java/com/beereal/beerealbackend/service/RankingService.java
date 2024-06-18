package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.JSONresponse.RankingResponse;
import com.beereal.beerealbackend.model.RankingEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface RankingService {
    List<RankingResponse> getRanking();

    RankingEntry addEntry(RankingEntry rankingEntry);
}
