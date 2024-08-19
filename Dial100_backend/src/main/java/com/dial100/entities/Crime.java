package com.dial100.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Crime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer crimeId;
	@Enumerated(EnumType.STRING)
	@Column(name = "crimeType")
	private CrimeType crimeType;
	private String description;
	private String penalty;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "crime_id") // foreign key in the Complaint entity
	private List<Complaint> complaints;
	
	public Crime() {}

	public Crime(Integer crimeId, CrimeType crimeType, String description, String penalty, List<Complaint> complaints) {
		super();
		this.crimeId = crimeId;
		this.crimeType = crimeType;
		this.description = description;
		this.penalty = penalty;
		this.complaints = complaints;
	}

	public Integer getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(Integer crimeId) {
		this.crimeId = crimeId;
	}

	public CrimeType getCrimeType() {
		return crimeType;
	}

	public void setCrimeType(CrimeType crimeType) {
		this.crimeType = crimeType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

}
