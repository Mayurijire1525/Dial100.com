package com.dial100.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dial100.entities.Investigation;

public interface InvestigationRepository extends JpaRepository<Investigation, Integer> {
	
	
}