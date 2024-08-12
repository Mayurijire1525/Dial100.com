package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
}
