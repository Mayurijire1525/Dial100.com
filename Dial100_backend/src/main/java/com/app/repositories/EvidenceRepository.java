package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Evidence;

public interface EvidenceRepository extends JpaRepository<Evidence, Integer> {
}
