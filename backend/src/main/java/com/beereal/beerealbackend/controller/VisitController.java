package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.dto.VisitDTO;
import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.model.Visit;
import com.beereal.beerealbackend.service.BarService;
import com.beereal.beerealbackend.service.UserService;
import com.beereal.beerealbackend.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;
    @Autowired
    private UserService userService;
    @Autowired
    private BarService barService;

    @PostMapping("/")
    public ResponseEntity<String> addVisit(@RequestBody VisitDTO visitDTO) {
        User user = userService.getUserByID(visitDTO.getUserID());
        if (user == null)
            return ResponseEntity.badRequest().body("User not found");

        Bar bar = barService.getBarByID(visitDTO.getBarID());
        if (bar == null)
            return ResponseEntity.badRequest().body("Bar not found");

        Visit visit = new Visit();
        visit.setUser(user);
        visit.setBar(bar);
        visit.setDate(visitDTO.getDate());

        Visit savedVisit = visitService.addVisit(visit);
        if (savedVisit == null)
            return ResponseEntity.status(500).body("Failed to add visit");
        return ResponseEntity.ok("Visit added successfully");
    }




}