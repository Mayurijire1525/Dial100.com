package com.dial100.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dial100.entities.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
	
	@Query("SELECT c FROM Complaint c WHERE c.user.id = :userId")
    List<Complaint> findComplaintsByUserId(@Param("userId") Integer userId);
    
    @Query("SELECT c FROM Complaint c WHERE c.status = 'new'")
    List<Complaint> findComplaintsByStatusNew();
}
