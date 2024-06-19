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

    /***
     * Zwraca listę wszystkich barów w bazie danych
     * @return Lista wszystkich obiektów Bar
     */
    @Override
    public List<Bar> getAllBars() {
        return barRepository.findAll();
    }

    /***
     * Dodaje do bazy danych podany bar
     * @param bar Obiekt dodawanego baru
     * @return Dodany bar
     */
    @Override
    public Bar addBar(Bar bar) {

        return barRepository.save(bar);
    }

    /**
     * Pobiera z bazy danych obiekt Bar o podanym Id
     * @param id id szukanego baru
     * @return obiekt Bar
     */
    @Override
    public Bar getBarByID(int id) {
        Optional<Bar> barOptional = barRepository.findById(id);
        return barOptional.orElse(null);
    }

    /**
     * Pobiera z bazy danych losowy bar
     * @return obiekt Bar losowy wybrany z bazy danych
     */
    @Override
    public Bar getRandomBar() {
        List<Bar> bars = getAllBars();
        return bars.get(randomNumberGenerator.getIntBetween(0, bars.size()));
    }


}