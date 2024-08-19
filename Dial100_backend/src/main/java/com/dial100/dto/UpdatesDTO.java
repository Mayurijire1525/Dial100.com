package com.dial100.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dial100.entities.Status;

public class UpdatesDTO {

	private Integer updateId;
	private LocalDate updateDate;
	private Status status;
	private String remarks;
	private Integer authorityId;
	private Integer complaintId;

	public UpdatesDTO() {
	}

	public UpdatesDTO(Integer updateId, LocalDate updateDate, Status status, String remarks, Integer authorityId,
			Integer complaintId) {
		super();
		this.updateId = updateId;
		this.updateDate = updateDate;
		this.status = status;
		this.remarks = remarks;
		this.authorityId = authorityId;
		this.complaintId = complaintId;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	@Override
	public String toString() {
		return "UpdatesDTO [updateId=" + updateId + ", updateDate=" + updateDate + ", status=" + status + ", remarks="
				+ remarks + ", authorityId=" + authorityId + ", complaintId=" + complaintId + "]";
	}
	
	

}
