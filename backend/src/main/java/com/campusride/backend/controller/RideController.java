package com.campusride.backend.controller;

import com.campusride.backend.dto.RideRequestDTO;
import com.campusride.backend.entity.Ride;
import com.campusride.backend.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/create")
    public Ride createRide(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    @GetMapping
    public List<RideRequestDTO> getAllRides() {
        return rideService.getAllRideDetails();
    }
}