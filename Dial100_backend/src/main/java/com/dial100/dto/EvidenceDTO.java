package com.dial100.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dial100.entities.EvidenceType;

public class EvidenceDTO {

	private Integer evidenceId;
	private String evidenceType;
	private String filePath;

	private LocalDate uploadDate;

	private Integer complaintId; // Add this field

	public EvidenceDTO() {
	}

	public EvidenceDTO(Integer evidenceId, String evidenceType, String filePath, LocalDate uploadDate,
			Integer complaintId) {
		this.evidenceId = evidenceId;
		this.evidenceType = evidenceType;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.complaintId = complaintId;
	}

	public Integer getEvidenceId() {
		return evidenceId;
	}

	public void setEvidenceId(Integer evidenceId) {
		this.evidenceId = evidenceId;
	}

	public String getEvidenceType() {
		return evidenceType;
	}

	public void setEvidenceType(String evidenceType) {
		this.evidenceType = evidenceType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	
}
