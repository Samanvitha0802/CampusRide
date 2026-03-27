package com.campusride.backend.service;

import com.campusride.backend.dto.*;
import com.campusride.backend.entity.*;
import com.campusride.backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private RideRepository rideRepo;

    public DashboardResponse getDashboard(String email) {

        // 1. Get user details
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DashboardResponse res = new DashboardResponse();

        res.name = user.getFullName();
        res.email = user.getEmail();
        res.phone = user.getPhone();

        // 2. Get vehicles
        res.vehicles = vehicleRepo.findByEmail(email)
                .stream()
                .map(v -> {
                    VehicleDTO dto = new VehicleDTO();
                    dto.type = v.getVehicleModel();
                    dto.number = v.getVehicleNumber();
                    dto.status = v.getStatus();   // ✅ FIXED HERE
                    return dto;
                }).toList();

        // 3. Get rides offered
        res.ridesOffered = rideRepo.findByDriverEmail(email)
                .stream()
                .map(r -> {
                    RideDTO dto = new RideDTO();
                    dto.pickup = r.getSource();
                    dto.destination = r.getDestination();
                    dto.time = r.getRideDate() + " " + r.getRideTime();
                    return dto;
                }).toList();

        // 4. Rides used (not implemented yet)
        res.ridesUsed = List.of();

        return res;
    }
}