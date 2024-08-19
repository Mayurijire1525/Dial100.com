package com.dial100.repositories;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dial100.entities.Updates;

public interface UpdatesRepository extends JpaRepository<Updates, Integer> {
	
	List<Updates> findByComplaint_ComplaintId(Integer complaintId);
    
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO updates (update_id, remarks, status, update_date, authority_id, complaint_id) " +
                   "VALUES (:updateId, :remarks, :status, :updateDate, :authorityId, :complaintId)", 
           nativeQuery = true)
    void insertUpdateRecord(@Param("updateId") Integer updateId, 
                            @Param("remarks") String remarks, 
                            @Param("status") String status, 
                            @Param("updateDate") LocalDate updateDate, 
                            @Param("authorityId") Integer authorityId, 
                            @Param("complaintId") Integer complaintId);
}