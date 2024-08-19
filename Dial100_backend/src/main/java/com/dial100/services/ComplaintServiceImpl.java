package com.dial100.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dial100.dto.ComplaintDTO;
import com.dial100.dto.CrimeDTO;
import com.dial100.dto.FetchComplaintDTO;
import com.dial100.entities.Complaint;
import com.dial100.entities.Crime; 
import com.dial100.entities.User;
import com.dial100.exception.ResourceNotFoundException;
import com.dial100.repositories.ComplaintRepository;
import com.dial100.repositories.CrimeRepository;
import com.dial100.repositories.UserRepository;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CrimeRepository crimeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ComplaintDTO> getAllComplaints() {
        List<Complaint> complaints = complaintRepository.findAll();
        return complaints.stream().map(complaint -> modelMapper.map(complaint, ComplaintDTO.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ComplaintDTO> getComplaintsByUserId(Integer userId) {
        List<Complaint> complaints = complaintRepository.findComplaintsByUserId(userId);
        return complaints.stream()
                .map(complaint -> modelMapper.map(complaint, ComplaintDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ComplaintDTO> getNewComplaints() {
        List<Complaint> complaints = complaintRepository.findComplaintsByStatusNew();
        return complaints.stream()
                .map(complaint -> modelMapper.map(complaint, ComplaintDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FetchComplaintDTO getComplaintById(Integer id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
        
        Crime crime = complaint.getCrime(); 

        FetchComplaintDTO fetchComplaintDTO = modelMapper.map(complaint, FetchComplaintDTO.class);
        CrimeDTO crimeDTO = modelMapper.map(crime, CrimeDTO.class);

        fetchComplaintDTO.setCrime(crimeDTO);  

        return fetchComplaintDTO;
    }

    @Override
    public ComplaintDTO createComplaint(ComplaintDTO complaintDTO) {
        User user = userRepository.findById(complaintDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Crime crime = crimeRepository.findById(complaintDTO.getCrimeId())
                .orElseThrow(() -> new ResourceNotFoundException("Crime not found"));

        Complaint complaint = modelMapper.map(complaintDTO, Complaint.class);
        complaint.setUser(user);
        complaint.setCrime(crime);

        Complaint savedComplaint = complaintRepository.save(complaint);
        return modelMapper.map(savedComplaint, ComplaintDTO.class);
    }

    @Override
    public ComplaintDTO updateComplaint(Integer id, ComplaintDTO complaintDTO) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        modelMapper.map(complaintDTO, complaint);
        
        if (complaintDTO.getCrimeId() != null) {
            Crime crime = crimeRepository.findById(complaintDTO.getCrimeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Crime not found"));
            complaint.setCrime(crime);
        }
        
        Complaint updatedComplaint = complaintRepository.save(complaint);
        return modelMapper.map(updatedComplaint, ComplaintDTO.class);
    }

    @Override
    public void deleteComplaint(Integer id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
        complaintRepository.delete(complaint);
    }
}
