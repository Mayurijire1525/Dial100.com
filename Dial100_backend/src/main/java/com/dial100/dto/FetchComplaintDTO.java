package com.dial100.dto;

import java.time.LocalDate;

public class FetchComplaintDTO {
	private Integer complaintId;
	private String description;
	private LocalDate dateFiled;
	private String status;
	private Integer userId;
	private CrimeDTO crime; // Replace crimeId with CrimeDTO

	// Constructors, Getters, and Setters
	public FetchComplaintDTO() {}

	public FetchComplaintDTO(Integer complaintId, String description, LocalDate dateFiled, String status, 
                        Integer userId, CrimeDTO crime) {
        this.complaintId = complaintId;
        this.description = description;
        this.dateFiled = dateFiled;
        this.status = status;
        this.userId = userId;
        this.crime = crime;  // Initialize CrimeDTO
    }

	// Other getters and setters

	public CrimeDTO getCrime() {
		return crime;
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateFiled() {
		return dateFiled;
	}

	public void setDateFiled(LocalDate dateFiled) {
		this.dateFiled = dateFiled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setCrime(CrimeDTO crime) {
		this.crime = crime;
	}
}
