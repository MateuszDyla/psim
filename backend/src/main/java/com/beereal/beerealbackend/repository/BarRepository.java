package com.beereal.beerealbackend.repository;

import com.beereal.beerealbackend.model.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BarRepository extends JpaRepository<Bar, Integer> {
}
