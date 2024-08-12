package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Investigation;

public interface InvestigationRepository extends JpaRepository<Investigation, Integer> {
}