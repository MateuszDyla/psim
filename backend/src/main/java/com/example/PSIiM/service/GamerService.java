package com.example.PSIiM.service;

import com.example.PSIiM.model.Gamer;

import java.util.List;

public interface GamerService {
    Gamer registerGamer(Gamer gamer);
    Gamer loginGamer(String login,String password);
    List<Gamer> getRanking();
    List<String> getVisitedBars(int userId);
    void visitBar(int userId, int barId);
    void endGame(int userId);
    List<String> getAllComments(int barId);
    void addComment(int barId, String comment);
    List<String> getVisitedBarsDetails(int userId);
    List<String> getBarDetails(int barId);
}
