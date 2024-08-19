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

import com.dial100.dto.UpdatesDTO;
import com.dial100.services.UpdatesService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/updates")
public class UpdatesController {

	@Autowired
	private UpdatesService updatesService;

	@GetMapping("/complaint/{complaintId}")
    public ResponseEntity<List<UpdatesDTO>> getUpdatesByComplaintId(@PathVariable("complaintId") Integer complaintId) {
        List<UpdatesDTO> updates = updatesService.getUpdatesByComplaintId(complaintId);
        return ResponseEntity.ok(updates);
    }
	
	@GetMapping
	public ResponseEntity<List<UpdatesDTO>> getAllUpdates() {
		List<UpdatesDTO> updates = updatesService.getAllUpdates();
		return ResponseEntity.ok(updates);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UpdatesDTO> getUpdateById(@PathVariable Integer id) {
		UpdatesDTO update = updatesService.getUpdateById(id);
		return ResponseEntity.ok(update);
	}

	@PostMapping
	public ResponseEntity<UpdatesDTO> createUpdate(@RequestBody UpdatesDTO updatesDTO) {
		System.out.println(updatesDTO.toString());
		UpdatesDTO newUpdate = updatesService.createUpdate(updatesDTO);
		return ResponseEntity.ok(newUpdate);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UpdatesDTO> updateUpdate(@PathVariable Integer id, @RequestBody UpdatesDTO updatesDTO) {
		UpdatesDTO updatedUpdate = updatesService.updateUpdate(id, updatesDTO);
		return ResponseEntity.ok(updatedUpdate);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUpdate(@PathVariable Integer id) {
		updatesService.deleteUpdate(id);
		return ResponseEntity.noContent().build();
	}
}