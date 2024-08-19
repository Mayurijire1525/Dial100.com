package com.dial100.services;

import java.util.List;

import com.dial100.dto.UpdatesDTO;

public interface UpdatesService {
	List<UpdatesDTO> getAllUpdates();
    UpdatesDTO getUpdateById(Integer id);
    List<UpdatesDTO> getUpdatesByComplaintId(Integer complaintId);
    UpdatesDTO createUpdate(UpdatesDTO updatesDTO);
    UpdatesDTO updateUpdate(Integer id, UpdatesDTO updatesDTO);
    void deleteUpdate(Integer id);
}