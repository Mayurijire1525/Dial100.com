package com.dial100.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dial100.dto.CrimeDTO;
import com.dial100.entities.Crime;
import com.dial100.exception.ResourceNotFoundException;
import com.dial100.repositories.CrimeRepository;

@Service
public class CrimeServiceImpl implements CrimeService {

	@Autowired
	private CrimeRepository crimeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CrimeDTO> getAllCrimes() {
		List<Crime> crimes = crimeRepository.findAll();
		return crimes.stream().map(crime -> modelMapper.map(crime, CrimeDTO.class)).collect(Collectors.toList());
	}

	@Override
	public CrimeDTO getCrimeById(Integer id) {
		Crime crime = crimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Crime not found"));
		return modelMapper.map(crime, CrimeDTO.class);
	}

	@Override
	public CrimeDTO createCrime(CrimeDTO crimeDTO) {
		Crime crime = modelMapper.map(crimeDTO, Crime.class);
		Crime savedCrime = crimeRepository.save(crime);
		return modelMapper.map(savedCrime, CrimeDTO.class);
	}

	@Override
	public CrimeDTO updateCrime(Integer id, CrimeDTO crimeDTO) {
		Crime crime = crimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Crime not found"));
		modelMapper.map(crimeDTO, crime);
		Crime updatedCrime = crimeRepository.save(crime);
		return modelMapper.map(updatedCrime, CrimeDTO.class);
	}

	@Override
	public void deleteCrime(Integer id) {
		Crime crime = crimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Crime not found"));
		crimeRepository.delete(crime);
	}
}
