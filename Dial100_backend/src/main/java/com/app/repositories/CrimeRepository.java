package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Crime;

public interface CrimeRepository extends JpaRepository<Crime, Integer> {
}
