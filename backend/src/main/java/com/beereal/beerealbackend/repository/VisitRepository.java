package com.beereal.beerealbackend.repository;

import com.beereal.beerealbackend.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
    List<Visit> findByUserIdOrderByDate(int userId);
    List<Visit> findByBarIdAndUserId(int userId, int barId);
    int countVisitByBarId(int barId);
}