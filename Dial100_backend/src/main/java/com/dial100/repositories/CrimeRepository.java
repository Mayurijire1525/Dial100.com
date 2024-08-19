package com.dial100.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dial100.entities.Crime;

public interface CrimeRepository extends JpaRepository<Crime, Integer> {
}
