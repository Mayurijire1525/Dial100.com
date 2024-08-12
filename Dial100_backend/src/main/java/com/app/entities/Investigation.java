package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Investigation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer investigationId;

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String report;

	@OneToOne
	@JoinColumn(name = "complaint_id", unique = true) // This will create the foreign key in the Investigation table
	private Complaint complaint;

	public Investigation(Integer investigationId, LocalDateTime startDate, LocalDateTime endDate, String report,
			Complaint complaint) {
		super();
		this.investigationId = investigationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.report = report;
		this.complaint = complaint;
	}

	public Integer getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(Integer investigationId) {
		this.investigationId = investigationId;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

}
