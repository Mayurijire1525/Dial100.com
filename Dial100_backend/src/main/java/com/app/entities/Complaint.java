package com.app.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer complaintId;

	private String description;
	private LocalDateTime dateFiled;
	private String status;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "complaint_id") // foreign key in the Evidence entity
	private List<Evidence> evidenceList;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "complaint_id") // foreign key in the Updates entity
	private List<Updates> updates;

	public Complaint(Integer complaintId, String description, LocalDateTime dateFiled, String status,
			List<Evidence> evidenceList, List<Updates> updates) {
		super();
		this.complaintId = complaintId;
		this.description = description;
		this.dateFiled = dateFiled;
		this.status = status;
		this.evidenceList = evidenceList;
		this.updates = updates;
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

	public LocalDateTime getDateFiled() {
		return dateFiled;
	}

	public void setDateFiled(LocalDateTime dateFiled) {
		this.dateFiled = dateFiled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Evidence> getEvidenceList() {
		return evidenceList;
	}

	public void setEvidenceList(List<Evidence> evidenceList) {
		this.evidenceList = evidenceList;
	}

	public List<Updates> getUpdates() {
		return updates;
	}

	public void setUpdates(List<Updates> updates) {
		this.updates = updates;
	}

}
