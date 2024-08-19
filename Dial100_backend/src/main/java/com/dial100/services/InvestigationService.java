package com.dial100.services;

import java.util.List;

import com.dial100.dto.InvestIDtoCompIDDTO;
import com.dial100.dto.InvestigationDTO;

public interface InvestigationService {
	List<InvestigationDTO> getAllInvestigations();

	InvestigationDTO getInvestigationById(Integer id);

	InvestigationDTO createInvestigation(InvestigationDTO investigationDTO);

	InvestigationDTO updateInvestigation(Integer id, InvestigationDTO investigationDTO);

	void deleteInvestigation(Integer id);

	InvestIDtoCompIDDTO getComplaintId(Integer invid);
	
}
