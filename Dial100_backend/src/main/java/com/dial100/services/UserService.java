package com.dial100.services;

import java.util.List;

import com.dial100.dto.UserDTO;

public interface UserService {
	List<UserDTO> getAllUsers();

	UserDTO getUserById(Integer id);

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(Integer id, UserDTO userDTO);

	void deleteUser(Integer id);
	
	UserDTO authenticateUser(String email, String password);
}
