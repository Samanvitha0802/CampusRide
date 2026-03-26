package com.campusride.backend.controller;

import com.campusride.backend.dto.DashboardResponse;
import com.campusride.backend.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{email}")
    public DashboardResponse getDashboard(@PathVariable String email) {
        return dashboardService.getDashboard(email);
    }
}