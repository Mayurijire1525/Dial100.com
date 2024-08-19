package com.dial100.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String address;
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id") // foreign key in the Complaint entity
	private List<Complaint> complaints;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "authority_id") // foreign key in the Investigation entity
	private List<Investigation> investigations;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "authority_id") // foreign key in the Updates entity
	private List<Updates> updates;
	
	public User() {}
	public User(Integer userId, String name, String email, String password, String phone, String address, Role role,
			List<Complaint> complaints, List<Investigation> investigations, List<Updates> updates) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.complaints = complaints;
		this.investigations = investigations;
		this.updates = updates;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	public List<Investigation> getInvestigations() {
		return investigations;
	}
	public void setInvestigations(List<Investigation> investigations) {
		this.investigations = investigations;
	}
	public List<Updates> getUpdates() {
		return updates;
	}
	public void setUpdates(List<Updates> updates) {
		this.updates = updates;
	}
	
	

}