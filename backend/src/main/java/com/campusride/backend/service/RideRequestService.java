package com.campusride.backend.service;

import com.campusride.backend.entity.Ride;
import com.campusride.backend.entity.RideRequest;
import com.campusride.backend.repository.RideRepository;
import com.campusride.backend.repository.RideRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository rideRequestRepository;

    @Autowired
    private RideRepository rideRepository;

    public RideRequest requestRide(Long rideId, String email) {
        RideRequest req = new RideRequest();
        req.setRideId(rideId);
        req.setPassengerEmail(email);
        req.setStatus("PENDING");

        return rideRequestRepository.save(req);
    }

    public List<RideRequest> getPassengerRequests(String email) {
        return rideRequestRepository.findByPassengerEmail(email);
    }

    public RideRequest approveRequest(Long id) {
        RideRequest req = rideRequestRepository.findById(id).get();
        req.setStatus("APPROVED");

        Ride ride = rideRepository.findById(req.getRideId()).get();
        ride.setSeatsAvailable(ride.getSeatsAvailable() - 1);
        rideRepository.save(ride);

        return rideRequestRepository.save(req);
    }

    public RideRequest rejectRequest(Long id) {
        RideRequest req = rideRequestRepository.findById(id).get();
        req.setStatus("REJECTED");
        return rideRequestRepository.save(req);
    }
}