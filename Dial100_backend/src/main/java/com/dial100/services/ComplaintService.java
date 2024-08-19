package com.dial100.services;

import java.util.List;

import com.dial100.dto.ComplaintDTO;
import com.dial100.dto.FetchComplaintDTO;

public interface ComplaintService {
	List<ComplaintDTO> getAllComplaints();

	FetchComplaintDTO getComplaintById(Integer id);

	ComplaintDTO createComplaint(ComplaintDTO complaintDTO);

	ComplaintDTO updateComplaint(Integer id, ComplaintDTO complaintDTO);

	void deleteComplaint(Integer id);
	
	 List<ComplaintDTO> getComplaintsByUserId(Integer userId);
	 
	 List<ComplaintDTO> getNewComplaints();
	 
	 
}
