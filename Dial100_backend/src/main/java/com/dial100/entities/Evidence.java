package com.dial100.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Evidence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer evidenceId;
	@Enumerated(EnumType.STRING)
	@Column(name = "evidenceType")
	private EvidenceType evidenceType;
	private String filePath;
	private LocalDate uploadDate;
	
	@ManyToOne
    @JoinColumn(name = "complaint_id") // Foreign key column in Complaint table
    private Complaint complaint;
	
	public Evidence() {}

	public Evidence(Integer evidenceId, EvidenceType evidenceType, String filePath, LocalDate uploadDate) {
		super();
		this.evidenceId = evidenceId;
		this.evidenceType = evidenceType;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
	}

	public Integer getEvidenceId() {
		return evidenceId;
	}

	public void setEvidenceId(Integer evidenceId) {
		this.evidenceId = evidenceId;
	}

	public EvidenceType getEvidenceType() {
		return evidenceType;
	}

	public void setEvidenceType(EvidenceType evidenceType) {
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

}
