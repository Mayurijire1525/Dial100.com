package com.dial100.services;

import java.util.List;

import com.dial100.dto.CrimeDTO;

public interface CrimeService {
	List<CrimeDTO> getAllCrimes();

	CrimeDTO getCrimeById(Integer id);

	CrimeDTO createCrime(CrimeDTO crimeDTO);

	CrimeDTO updateCrime(Integer id, CrimeDTO crimeDTO);

	void deleteCrime(Integer id);
}
