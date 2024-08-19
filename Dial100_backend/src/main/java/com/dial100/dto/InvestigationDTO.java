package com.dial100.dto;

import java.time.LocalDate;

public class InvestigationDTO {

	private Integer investigationId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String report;
	private Integer authorityId; // Add this field for the authorityId
	private Integer complaintId; // Add this field for the complaintId
	
	public InvestigationDTO() {}

	public InvestigationDTO(Integer investigationId, LocalDate startDate, LocalDate endDate, String report,
			Integer authorityId, Integer complaintId) {
		super();
		this.investigationId = investigationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.report = report;
		this.authorityId = authorityId;
		this.complaintId = complaintId;
	}

	public Integer getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(Integer investigationId) {
		this.investigationId = investigationId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
}
