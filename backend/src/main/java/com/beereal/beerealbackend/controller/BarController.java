package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.service.BarService;
import com.beereal.beerealbackend.service.CommentService;
import com.beereal.beerealbackend.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bars")
public class BarController {
    @Autowired
    private BarService barService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<List<Bar>> getAllBars() {
        List<Bar> bars = barService.getAllBars();
        return ResponseEntity.ok(bars);
    }

    @GetMapping("/getRandom")
    public ResponseEntity<Bar> getRandomBar() {
        Bar bar = barService.getRandomBar();
        return ResponseEntity.ok(bar);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBar(@RequestBody Bar bar) {
        if (bar.getName() == null || bar.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Bar name is required");
        }

        if (bar.getAddress() == null || bar.getAddress().isEmpty()) {
            return ResponseEntity.badRequest().body("Bar address is required");
        }

        if (bar.getImageUrl() == null || bar.getImageUrl().isEmpty()) {
            return ResponseEntity.badRequest().body("Bar image URL is required");
        }

        Bar savedBar = barService.addBar(bar);
        if (savedBar == null) {
            return ResponseEntity.status(500).body("Failed to add bar");
        }

        return ResponseEntity.ok("Bar added successfully");
    }


}