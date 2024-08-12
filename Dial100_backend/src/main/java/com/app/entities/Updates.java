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
public class Updates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer updateId;

	private LocalDateTime updateDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	private String remarks;

	public Updates(Integer updateId, LocalDateTime updateDate, Status status, String remarks) {
		super();
		this.updateId = updateId;
		this.updateDate = updateDate;
		this.status = status;
		this.remarks = remarks;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
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

}
