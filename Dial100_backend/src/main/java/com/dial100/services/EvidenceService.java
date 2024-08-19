package com.dial100.services;

import java.util.List;

import com.dial100.dto.EvidenceDTO;

public interface EvidenceService {
	List<EvidenceDTO> getAllEvidences();

	EvidenceDTO getEvidenceById(Integer id);

	EvidenceDTO createEvidence(EvidenceDTO evidenceDTO);

	EvidenceDTO updateEvidence(Integer id, EvidenceDTO evidenceDTO);

	void deleteEvidence(Integer id);

	List<EvidenceDTO> getAllEvidencesByComplaintId(Integer complaintId);
}