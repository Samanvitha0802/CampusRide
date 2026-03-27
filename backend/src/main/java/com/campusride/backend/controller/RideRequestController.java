package com.campusride.backend.controller;

import com.campusride.backend.dto.RideRequestDTO;
import com.campusride.backend.entity.RideRequest;
import com.campusride.backend.service.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ride-request")
@CrossOrigin
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;

    @PostMapping("/request")
    public RideRequest requestRide(@RequestParam Long rideId,
                                   @RequestParam String email) {
        return rideRequestService.requestRide(rideId, email);
    }

    @GetMapping("/passenger")
    public List<RideRequestDTO> getPassengerRequests(@RequestParam String email) {
        return rideRequestService.getPassengerRequests(email);
    }

    @PutMapping("/{id}/approve")
    public RideRequest approve(@PathVariable Long id) {
        return rideRequestService.approveRequest(id);
    }

    @PutMapping("/{id}/reject")
    public RideRequest reject(@PathVariable Long id) {
        return rideRequestService.rejectRequest(id);
    }
}