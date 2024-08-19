package com.dial100.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dial100.dto.InvestIDtoCompIDDTO;
import com.dial100.dto.InvestigationDTO;
import com.dial100.entities.Complaint;
import com.dial100.entities.Investigation;
import com.dial100.entities.User;
import com.dial100.exception.ResourceNotFoundException;
import com.dial100.repositories.ComplaintRepository;
import com.dial100.repositories.InvestigationRepository;
import com.dial100.repositories.UserRepository;

@Service
public class InvestigationServiceImpl implements InvestigationService {

	@Autowired
	private InvestigationRepository investigationRepository;

	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<InvestigationDTO> getAllInvestigations() {
		List<Investigation> investigations = investigationRepository.findAll();
		return investigations.stream().map(investigation -> modelMapper.map(investigation, InvestigationDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public InvestigationDTO getInvestigationById(Integer id) {
		Investigation investigation = investigationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Investigation not found"));
		return modelMapper.map(investigation, InvestigationDTO.class);
	}

	@Override
	public InvestigationDTO createInvestigation(InvestigationDTO investigationDTO) {
		Complaint complaint = complaintRepository.findById(investigationDTO.getComplaintId())
				.orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

		complaint.setStatus("accepted");
		complaintRepository.save(complaint);

		Investigation investigation = new Investigation();
		investigation.setStartDate(investigationDTO.getStartDate());
		investigation.setEndDate(investigationDTO.getEndDate());
		investigation.setReport(investigationDTO.getReport());
		investigation.setComplaint(complaint);

		Integer authorityId = investigationDTO.getAuthorityId();
		System.out.println("Authority ID: " + authorityId);

		User findById = userRepository.findById(authorityId).get();
		investigation.setUser(findById);
		Investigation savedInvestigation = investigationRepository.save(investigation);
		return modelMapper.map(savedInvestigation, InvestigationDTO.class);
	}

	@Override
	public InvestigationDTO updateInvestigation(Integer id, InvestigationDTO investigationDTO) {
		Investigation investigation = investigationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Investigation not found"));

		Complaint complaint = complaintRepository.findById(investigationDTO.getComplaintId())
				.orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

		investigation.setStartDate(investigationDTO.getStartDate());
		investigation.setEndDate(investigationDTO.getEndDate());
		investigation.setReport(investigationDTO.getReport());
		investigation.setComplaint(complaint);

		Investigation updatedInvestigation = investigationRepository.save(investigation);
		return modelMapper.map(updatedInvestigation, InvestigationDTO.class);
	}

	@Override
	public void deleteInvestigation(Integer id) {
		Investigation investigation = investigationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Investigation not found"));
		investigationRepository.delete(investigation);
	}

	@Override
    public InvestIDtoCompIDDTO getComplaintId(Integer invid) {
		Investigation investigation = investigationRepository.findById(invid)
				.orElseThrow(() -> new ResourceNotFoundException("Investigation not found"));
		InvestIDtoCompIDDTO compid = new InvestIDtoCompIDDTO(investigation.getComplaint().getComplaintId());
		return compid;
    }

}
