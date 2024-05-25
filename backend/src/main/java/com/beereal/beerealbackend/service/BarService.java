package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Bar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BarService {
    List<Bar> getAllBars();
    Bar addBar(Bar bar);
    Bar getBarByID(int id);

    Bar getRandomBar();
}