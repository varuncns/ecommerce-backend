package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dto.FinancialInsightsDTO;
import com.ecommerce.dto.LogisticsDashboardDTO;
import com.ecommerce.service.AdminDashboardService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDashboardService adminDashboardService;
    
    @GetMapping("/dashboard")
    public String getAdminDashboard() {
        return "Welcome Admin! This is your dashboard.";
    }
    
    @GetMapping("/logistics")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LogisticsDashboardDTO> getLogisticsSummary() {
        return ResponseEntity.ok(adminDashboardService.getLogisticsSummary());
    }
    
    @GetMapping("/financial-insights")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FinancialInsightsDTO> getFinancialInsights() {
        return ResponseEntity.ok(adminDashboardService.getFinancialInsights());
    }
}
