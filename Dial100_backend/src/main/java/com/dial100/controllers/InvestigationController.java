package com.dial100.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import com.dial100.dto.InvestIDtoCompIDDTO;
import com.dial100.dto.InvestigationDTO;
import com.dial100.entities.Investigation;
import com.dial100.entities.User;
import com.dial100.exception.ResourceNotFoundException;
import com.dial100.repositories.UserRepository;
import com.dial100.services.InvestigationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/investigations")
public class InvestigationController {

	@Autowired
	private InvestigationService investigationService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<InvestigationDTO>> getAllInvestigations() {
		List<InvestigationDTO> investigations = investigationService.getAllInvestigations();
		return ResponseEntity.ok(investigations);
	}

	@GetMapping("/getCompId/{invid}")
	public ResponseEntity<InvestIDtoCompIDDTO> getAuthorityId(@PathVariable Integer invid) {
	    InvestIDtoCompIDDTO complaintId = investigationService.getComplaintId(invid);
	    return ResponseEntity.ok(complaintId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InvestigationDTO> getInvestigationById(@PathVariable Integer id) {
		InvestigationDTO investigation = investigationService.getInvestigationById(id);
		return ResponseEntity.ok(investigation);
	}

	@GetMapping("/authority/{authorityId}")
	public ResponseEntity<?> getAllInvestigationsByAuthorityId(@PathVariable("authorityId") Integer authorityId) {
		User user = userRepository.findById(authorityId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		List<Investigation> investigations = user.getInvestigations();
		List<InvestigationDTO> collect = investigations.stream()
				.map(invest -> modelMapper.map(invest, InvestigationDTO.class)).collect(Collectors.toList());

		return ResponseEntity.ok(collect);
	}

	@PostMapping
	public ResponseEntity<InvestigationDTO> createInvestigation(@RequestBody InvestigationDTO investigationDTO) {
		System.out.println("Received InvestigationDTO: " + investigationDTO.getAuthorityId());
		InvestigationDTO newInvestigation = investigationService.createInvestigation(investigationDTO);
		return ResponseEntity.ok(newInvestigation);
	}

	@PutMapping("/{id}")
	public ResponseEntity<InvestigationDTO> updateInvestigation(@PathVariable Integer id,
			@RequestBody InvestigationDTO investigationDTO) {
		InvestigationDTO updatedInvestigation = investigationService.updateInvestigation(id, investigationDTO);
		return ResponseEntity.ok(updatedInvestigation);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInvestigation(@PathVariable Integer id) {
		investigationService.deleteInvestigation(id);
		return ResponseEntity.noContent().build();
	}
}
