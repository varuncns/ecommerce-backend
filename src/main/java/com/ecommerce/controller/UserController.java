package com.ecommerce.controller;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Accessible only for authenticated user
    @GetMapping("/profile")
    public UserDTO getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUserProfileByEmail(userDetails.getUsername());
    }

    // For admin to list all users
    @GetMapping("/admin/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
