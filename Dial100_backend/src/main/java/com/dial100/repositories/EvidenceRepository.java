package com.dial100.repositories;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dial100.entities.Evidence;
import com.dial100.entities.EvidenceType;

public interface EvidenceRepository extends JpaRepository<Evidence, Integer> {
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO evidence (evidence_id, evidence_type, file_path, upload_date, complaint_id) " +
                   "VALUES (:evidenceId, :evidenceType, :filePath, :uploadDate, :complaintId)", 
           nativeQuery = true)
    void insertEvidence(Integer evidenceId, String evidenceType, String filePath, LocalDate uploadDate, Integer complaintId);
}
