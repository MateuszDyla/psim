package com.beereal.beerealbackend.repository;

import com.beereal.beerealbackend.model.RankingEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<RankingEntry, Integer> {
}
