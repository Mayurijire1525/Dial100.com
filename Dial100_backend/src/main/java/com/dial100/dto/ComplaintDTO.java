package com.dial100.dto;

import java.time.LocalDate;
import java.util.List;

public class ComplaintDTO {
    private Integer complaintId;
    private String description;
    private LocalDate dateFiled; // Changed from LocalDateTime to LocalDate
    private String status;
    private Integer userId; // Add userId
    private Integer crimeId; // Add crimeId

    public ComplaintDTO() {}

    public ComplaintDTO(Integer complaintId, String description, LocalDate dateFiled, String status,
                        Integer userId, Integer crimeId) {
        super();
        this.complaintId = complaintId;
        this.description = description;
        this.dateFiled = dateFiled; // Initialize dateFiled
        this.status = status;
        this.userId = userId; // Initialize userId
        this.crimeId = crimeId; // Initialize crimeId
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

    public Integer getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(Integer crimeId) {
        this.crimeId = crimeId;
    }
}
