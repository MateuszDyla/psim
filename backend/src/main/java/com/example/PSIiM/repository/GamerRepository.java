package com.example.PSIiM.repository;

import com.example.PSIiM.model.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends JpaRepository<Gamer,Integer> {

    Gamer findByLoginAndPassword(String login, String password);

    boolean findByLogin(String login);
}
