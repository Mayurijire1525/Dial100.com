package com.dial100.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dial100.dto.CrimeDTO;
import com.dial100.services.CrimeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/crimes")
public class CrimeController {

	@Autowired
	private CrimeService crimeService;

	@GetMapping
	public ResponseEntity<List<CrimeDTO>> getAllCrimes() {
		List<CrimeDTO> crimes = crimeService.getAllCrimes();
		return ResponseEntity.ok(crimes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CrimeDTO> getCrimeById(@PathVariable Integer id) {
		CrimeDTO crime = crimeService.getCrimeById(id);
		return ResponseEntity.ok(crime);
	}

	@PostMapping
	public ResponseEntity<CrimeDTO> createCrime(@RequestBody CrimeDTO crimeDTO) {
		CrimeDTO newCrime = crimeService.createCrime(crimeDTO);
		return ResponseEntity.ok(newCrime);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CrimeDTO> updateCrime(@PathVariable Integer id, @RequestBody CrimeDTO crimeDTO) {
		CrimeDTO updatedCrime = crimeService.updateCrime(id, crimeDTO);
		return ResponseEntity.ok(updatedCrime);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCrime(@PathVariable Integer id) {
		crimeService.deleteCrime(id);
		return ResponseEntity.noContent().build();
	}
}
