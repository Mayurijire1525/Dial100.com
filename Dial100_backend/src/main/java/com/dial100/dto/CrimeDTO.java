package com.dial100.dto;

import java.util.List;

import com.dial100.entities.CrimeType;

public class CrimeDTO {
	private Integer crimeId;
	private CrimeType crimeType;
	private String description;
	private String penalty;
	
	public CrimeDTO() {}

	public CrimeDTO(Integer crimeId, CrimeType crimeType, String description, String penalty) {
		super();
		this.crimeId = crimeId;
		this.crimeType = crimeType;
		this.description = description;
		this.penalty = penalty;
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

}
