package com.ecommerce.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String getAdminDashboard() {
        return "Welcome Admin! This is your dashboard.";
    }
}
