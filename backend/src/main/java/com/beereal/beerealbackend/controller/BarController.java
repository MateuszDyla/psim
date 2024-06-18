package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.JSONresponse.BarDetailsResponse;
import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.service.BarService;
import com.beereal.beerealbackend.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bars")
public class BarController {
    private final BarService barService;

    private final VisitService visitService;

    public BarController(BarService barService, VisitService visitService) {
        this.barService = barService;
        this.visitService = visitService;
    }

    /***
     *
     * @return Lista wszystkich barów
     */
    @GetMapping("/")
    public ResponseEntity<List<Bar>> getAllBars() {
        List<Bar> bars = barService.getAllBars();
        return ResponseEntity.ok(bars);
    }

    /***
     *
     * @return Losowo wybrany bar
     */
    @GetMapping("/getRandom")
    public ResponseEntity<Bar> getRandomBar() {
        Bar bar = barService.getRandomBar();
        return ResponseEntity.ok(bar);
    }

    /***
     *
     * @return Id losowo wybranego baru
     */
    @GetMapping("/getRandomId")
    public ResponseEntity<Integer> getRandomBarId() {
        Bar bar = barService.getRandomBar();
        return ResponseEntity.ok(bar.getId());
    }

    /***
     *
     * @param bar
     * @return String ze szczegółami wykonanej operacji
     */
    @PostMapping("/")
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

    /***
     *
     * @param barId id baru
     * @param userId id użytkownika
     * @return Statystyki odwiedzin podanego baru przez podanego uzytkownika
     */
    @GetMapping("{barId}/user/{userId}")
    public ResponseEntity<BarDetailsResponse> getBarVisitedByUserDetails(@PathVariable int barId, @PathVariable int userId) {
        return ResponseEntity.ok(visitService.getBarVisitedByDetails(barId, userId));
    }

    /***
     *
     * @param barId id bary
     * @return Obiekt baru
     */
    @GetMapping("/{barId}")
    public ResponseEntity<Bar> getBar(@PathVariable int barId) {
        Bar bar = barService.getBarByID(barId);
        if (bar == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(bar);
    }


}