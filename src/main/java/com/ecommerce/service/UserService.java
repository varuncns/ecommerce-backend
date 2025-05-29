package com.ecommerce.service;

import com.ecommerce.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers(); // For admin
    UserDTO getUserProfileByEmail(String email); // For /user/profile
}
