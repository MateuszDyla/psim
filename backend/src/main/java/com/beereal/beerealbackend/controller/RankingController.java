package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.JSONresponse.RankingResponse;
import com.beereal.beerealbackend.dto.RankingEntryDTO;
import com.beereal.beerealbackend.model.RankingEntry;
import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.service.RankingService;
import com.beereal.beerealbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    private final RankingService rankingService;
    final
    UserService userService;

    public RankingController(RankingService rankingService, UserService userService) {
        this.rankingService = rankingService;
        this.userService = userService;
    }

    /***
     * Zwraca wszystkie wiersze rankingu w postaci RankingResponse
     * @return Lista RankingResponse
     */
    @GetMapping("/")
    public ResponseEntity<List<RankingResponse>> getRanking() {
        List<RankingResponse> list = rankingService.getRanking();
        return ResponseEntity.ok(list);
    }

    /**
     * Dodaje nowy wpis do rankingu
     * Wywołane po zakończeniu gry
     * @param rankingEntryDTO
     * @return Status operacji
     */
    @PostMapping("/")
    public ResponseEntity<String> addEntry(@RequestBody RankingEntryDTO rankingEntryDTO) {
        User user = userService.getUserByID(rankingEntryDTO.getUserID());
        if (user == null)
            return ResponseEntity.badRequest().body("User not found");

        RankingEntry rankingEntry = new RankingEntry(user, rankingEntryDTO.getVisitedBarsNumber(), rankingEntryDTO.getGameplayTime());
        RankingEntry savedEntry = rankingService.addEntry(rankingEntry);
        if (savedEntry == null)
            return ResponseEntity.status(500).body("Failed to add ranking entry");
        return ResponseEntity.ok("Ranking entry added succesfully");
    }
}
