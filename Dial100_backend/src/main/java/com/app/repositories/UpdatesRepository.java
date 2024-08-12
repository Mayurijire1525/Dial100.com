package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Updates;

public interface UpdatesRepository extends JpaRepository<Updates, Integer> {
}