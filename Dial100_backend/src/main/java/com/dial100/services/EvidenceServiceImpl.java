package com.dial100.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dial100.dto.EvidenceDTO;
import com.dial100.entities.Complaint;
import com.dial100.entities.Evidence;
import com.dial100.exception.ResourceNotFoundException;
import com.dial100.repositories.ComplaintRepository;
import com.dial100.repositories.EvidenceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class EvidenceServiceImpl implements EvidenceService {

    @Autowired
    private EvidenceRepository evidenceRepository;
    
    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EvidenceDTO> getAllEvidences() {
        List<Evidence> evidences = evidenceRepository.findAll();
        return evidences.stream().map(evidence -> modelMapper.map(evidence, EvidenceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EvidenceDTO getEvidenceById(Integer id) {
        Evidence evidence = evidenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evidence not found"));
        return modelMapper.map(evidence, EvidenceDTO.class);
    }

    @Override
    public EvidenceDTO createEvidence(EvidenceDTO evidenceDTO) {
        evidenceRepository.insertEvidence(evidenceDTO.getEvidenceId(),evidenceDTO.getEvidenceType(),evidenceDTO.getFilePath(),evidenceDTO.getUploadDate(),evidenceDTO.getComplaintId());
        return evidenceDTO;
    }

    @Override
    public EvidenceDTO updateEvidence(Integer id, EvidenceDTO evidenceDTO) {
        Evidence evidence = evidenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evidence not found"));
        modelMapper.map(evidenceDTO, evidence);
        Evidence updatedEvidence = evidenceRepository.save(evidence);
        return modelMapper.map(updatedEvidence, EvidenceDTO.class);
    }

    @Override
    public void deleteEvidence(Integer id) {
        Evidence evidence = evidenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evidence not found"));
        evidenceRepository.delete(evidence);
    }

	@Override
	@Transactional
	public List<EvidenceDTO> getAllEvidencesByComplaintId(Integer complaintId) {
		Complaint complaint = complaintRepository.findById(complaintId)
				.orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
		List<Evidence> evidences = complaint.getEvidenceList();
		List<EvidenceDTO> dEvidences = evidences.stream()
				.map(evi-> modelMapper.map(evi, EvidenceDTO.class)).collect(Collectors.toList());
		return dEvidences;
	}
}
