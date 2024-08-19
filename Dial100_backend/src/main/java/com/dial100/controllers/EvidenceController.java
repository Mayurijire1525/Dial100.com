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

import com.dial100.dto.EvidenceDTO;
import com.dial100.services.EvidenceService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/evidences")
public class EvidenceController {

    @Autowired
    private EvidenceService evidenceService;

    @GetMapping
    public ResponseEntity<List<EvidenceDTO>> getAllEvidences() {
        List<EvidenceDTO> evidences = evidenceService.getAllEvidences();
        return ResponseEntity.ok(evidences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvidenceDTO> getEvidenceById(@PathVariable Integer id) {
        EvidenceDTO evidence = evidenceService.getEvidenceById(id);
        return ResponseEntity.ok(evidence);
    }
    
    @GetMapping("/complaint/{complaintId}")
    public ResponseEntity<?> getAllEvidencesByComplaintId(@PathVariable("complaintId") Integer complaintId){
    	List<EvidenceDTO> evidences=evidenceService.getAllEvidencesByComplaintId(complaintId);
    	return ResponseEntity.ok(evidences);
    }

    @PostMapping
    public ResponseEntity<EvidenceDTO> createEvidence(@RequestBody EvidenceDTO evidenceDTO) {
        EvidenceDTO newEvidence = evidenceService.createEvidence(evidenceDTO);
        return ResponseEntity.ok(newEvidence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvidenceDTO> updateEvidence(@PathVariable Integer id, @RequestBody EvidenceDTO evidenceDTO) {
        EvidenceDTO updatedEvidence = evidenceService.updateEvidence(id, evidenceDTO);
        return ResponseEntity.ok(updatedEvidence);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvidence(@PathVariable Integer id) {
        evidenceService.deleteEvidence(id);
        return ResponseEntity.noContent().build();
    }
}
