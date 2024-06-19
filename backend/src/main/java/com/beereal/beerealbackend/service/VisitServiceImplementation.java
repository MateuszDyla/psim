package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.JSONresponse.BarDetailsResponse;
import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Visit;
import com.beereal.beerealbackend.repository.BarRepository;
import com.beereal.beerealbackend.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImplementation implements VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private BarRepository barRepository;

    /**
     * Dodaje wizytę do bazy danych wizyt
     * @param visit dane dodawanego obiekty
     * @return dodawany obiekt
     */
    @Override
    public Visit addVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    /**
     * Zwraca listę odblokowanych barów przez użytkownika o podanym id (odblokowany = przynajmniej 1 wizyta danego użytkownika w danym barze nastąpiła)
     * @param userId id użytkownika
     * @return Lista odblokowanych barów
     */
    @Override
    public List<Bar> getUnlockedBarsByUserId(int userId) {
        ArrayList<Bar> bars = new ArrayList<>();
        List<Visit> visits = visitRepository.findByUserIdOrderByDate(userId);
        for (Visit visit : visits) {
            bars.add(visit.getBar());
        }
        return new ArrayList<>(new HashSet<>(bars));
    }

    /**
     * Liczy wizyty użytkownika o podanym id w barze o podanym id
     * @param barId id baru
     * @param userId id użytkownika
     * @return liczba wizyt użytkownika w podanym barze
     */
    @Override
    public int countVisitsByUser(int barId, int userId) {

        return visitRepository.findByBarIdAndUserId(barId, userId).size();
    }

    /**
     * Liczy wizyty wszystkich użytkowników w barze o podanym id
     * @param barId id baru
     * @return liczba wszystkich wizyt w barze
     */
    @Override
    public int countVisitsInBar(int barId) {
        return visitRepository.countVisitByBarId(barId);
    }


    /**
     * Zwraca statystyki wizyt użytkownika/wszystkich użytkowników w danym barze
     * @param barId id baru
     * @param userId id użytkownika
     * @return statystyki wizyt
     */
    @Override
    public BarDetailsResponse getBarVisitedByDetails(int barId, int userId) {
        int visitsByUser = countVisitsByUser(barId, userId);
        int visits = countVisitsInBar(barId);
        //LocalDateTime lastVisit = getLastVisitDate(barId, userId);
        Optional<Bar> optionalBar = barRepository.findById(barId);
        if (optionalBar.isEmpty())
            return null;
        Bar bar = optionalBar.get();

        return new BarDetailsResponse(bar, visits, visitsByUser);
    }

}