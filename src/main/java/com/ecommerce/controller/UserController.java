package com.ecommerce.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String getProfile() {
        return "Welcome to your profile!";
    }
}
