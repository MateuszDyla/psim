package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.misc.RandomNumberGenerator;
import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.repository.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarServiceImplementation implements BarService {

    RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    @Autowired
    private BarRepository barRepository;

    @Override
    public List<Bar> getAllBars() {
        return barRepository.findAll();
    }

    @Override
    public Bar addBar(Bar bar) {
        return barRepository.save(bar);
    }

    @Override
    public Bar getBarByID(int id) {
        Optional<Bar> barOptional = barRepository.findById(id);
        return barOptional.orElse(null);
    }
    @Override
    public Bar getRandomBar() {
        List<Bar> bars = getAllBars();
        return bars.get(randomNumberGenerator.getIntBetween(0, bars.size()));
    }


}