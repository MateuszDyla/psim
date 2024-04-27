package com.example.PSIiM.service;

import com.example.PSIiM.model.Gamer;
import com.example.PSIiM.repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamerServiceImplementation implements GamerService{
    @Autowired
    private GamerRepository gamerRepository;

    @Override
    public Gamer registerGamer(Gamer gamer) {
        if (gamerRepository.findByLogin(gamer.getLogin()) != false) {
            throw new IllegalArgumentException("Użytkownik o podanym loginie już istnieje");
        }
        return gamerRepository.save(gamer);
    }

    @Override
    public Gamer loginGamer(String login, String password) {
        return gamerRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public List<Gamer> getRanking() {
        return gamerRepository.findAll();
    }

    @Override
    public List<String> getVisitedBars(int userId) {
        return null;
    }

    @Override
    public void visitBar(int userId, int barId) {

    }

    @Override
    public void endGame(int userId) {

    }

    @Override
    public List<String> getAllComments(int barId) {
        return null;
    }

    @Override
    public void addComment(int barId, String comment) {

    }
    @Override
    public List<String> getVisitedBarsDetails(int userId) {
        return null;
    }

    @Override
    public List<String> getBarDetails(int barId) {
        return null;
    }
}