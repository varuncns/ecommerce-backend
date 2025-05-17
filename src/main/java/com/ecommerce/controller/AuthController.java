package com.ecommerce.controller;

import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.AuthResponse;
import com.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    	System.out.println("🚀 /auth/register hit!");
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping("/admin/register")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }
}
