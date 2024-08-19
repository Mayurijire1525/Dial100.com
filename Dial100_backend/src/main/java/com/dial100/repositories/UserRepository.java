package com.dial100.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dial100.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
}