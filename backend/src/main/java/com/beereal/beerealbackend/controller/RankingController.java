package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.dto.RankingEntryDTO;
import com.beereal.beerealbackend.model.RankingEntry;
import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.service.RankingService;
import com.beereal.beerealbackend.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    @Autowired
    private RankingService rankingService;
    @Autowired
    UserService userService;

    @GetMapping("/show")
    public ResponseEntity<List<RankingEntry>> getRanking() {
        List<RankingEntry> list = rankingService.getRanking();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
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
