package com.campusride.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusride.backend.entity.Ride;
import com.campusride.backend.dto.RideRequestDTO;
import com.campusride.backend.service.RideService;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin(origins = "*")
public class RideController {

    @Autowired
    private RideService rideService;

    // Create ride
    @PostMapping("/create")
    public ResponseEntity<?> createRide(@RequestBody Ride ride) {
        Ride savedRide = rideService.createRide(ride);
        return ResponseEntity.ok(savedRide);
    }

    // Get all rides with driver contact
    @GetMapping("/all")
    public List<RideRequestDTO> getAllRides() {
        return rideService.getAllRideDetails();
    }
}
