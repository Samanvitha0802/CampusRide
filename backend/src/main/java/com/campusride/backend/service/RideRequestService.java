package com.campusride.backend.service;

import com.campusride.backend.dto.RideRequestDTO;
import com.campusride.backend.entity.Ride;
import com.campusride.backend.entity.RideRequest;
import com.campusride.backend.entity.User;
import com.campusride.backend.repository.RideRepository;
import com.campusride.backend.repository.RideRequestRepository;
import com.campusride.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository rideRequestRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    // Request Ride
    public RideRequest requestRide(Long rideId, String email) {
        RideRequest req = new RideRequest();
        req.setRideId(rideId);
        req.setPassengerEmail(email);
        req.setStatus("PENDING");

        return rideRequestRepository.save(req);
    }

    // My Requests
    public List<RideRequestDTO> getPassengerRequests(String email) {
        List<RideRequest> requests = rideRequestRepository.findByPassengerEmail(email);
        List<RideRequestDTO> list = new ArrayList<>();

        for (RideRequest req : requests) {
            Ride ride = rideRepository.findById(req.getRideId()).get();
            User driver = userRepository.findByEmail(ride.getDriverEmail()).get();

            RideRequestDTO dto = new RideRequestDTO();
            dto.setRideId(ride.getId());
            dto.setSource(ride.getSource());
            dto.setDestination(ride.getDestination());
            dto.setRideDate(ride.getRideDate());
            dto.setRideTime(ride.getRideTime());
            dto.setSeatsAvailable(ride.getSeatsAvailable());
            dto.setVehicleNumber(ride.getVehicleNumber());
            dto.setDriverName(driver.getFullName());
            dto.setDriverMobile(driver.getPhone());
            dto.setStatus(req.getStatus());

            list.add(dto);
        }

        return list;
    }

    // Approve Request
    public RideRequest approveRequest(Long id) {
        RideRequest req = rideRequestRepository.findById(id).get();
        req.setStatus("APPROVED");

        Ride ride = rideRepository.findById(req.getRideId()).get();
        ride.setSeatsAvailable(ride.getSeatsAvailable() - 1);
        rideRepository.save(ride);

        return rideRequestRepository.save(req);
    }

    // Reject Request
    public RideRequest rejectRequest(Long id) {
        RideRequest req = rideRequestRepository.findById(id).get();
        req.setStatus("REJECTED");
        return rideRequestRepository.save(req);
    }
}