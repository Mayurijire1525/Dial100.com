package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Evidence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer evidenceId;
	@Enumerated(EnumType.STRING)
	@Column(name = "evidenceType")
	private EvidenceType evidenceType;
	private String filePath;
	private LocalDateTime uploadDate;

	public Evidence(Integer evidenceId, EvidenceType evidenceType, String filePath, LocalDateTime uploadDate) {
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

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

}
