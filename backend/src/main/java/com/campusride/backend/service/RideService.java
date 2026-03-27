package com.campusride.backend.service;

import com.campusride.backend.dto.RideRequestDTO;
import com.campusride.backend.entity.Ride;
import com.campusride.backend.entity.User;
import com.campusride.backend.repository.RideRepository;
import com.campusride.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Ride
    public Ride createRide(Ride ride) {
        ride.setStatus("ACTIVE");
        return rideRepository.save(ride);
    }

    // Get All Available Rides (Today + Future Only)
    public List<RideRequestDTO> getAllRideDetails() {

        String today = LocalDate.now().toString();

        String currentTime = LocalTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm"));

        List<Ride> rides = rideRepository.findAvailableRides(today, currentTime);
        List<RideRequestDTO> list = new ArrayList<>();

        for (Ride ride : rides) {

            String driverName = "";
            String driverMobile = "";

            if (ride.getDriverEmail() != null) {
                User driver = userRepository
                        .findByEmail(ride.getDriverEmail())
                        .orElse(null);

                if (driver != null) {
                    driverName = driver.getFullName();
                    driverMobile = driver.getPhone();
                }
            }

            RideRequestDTO dto = new RideRequestDTO(
                    ride.getId(),
                    ride.getSource(),
                    ride.getDestination(),
                    ride.getRideDate(),
                    ride.getRideTime(),
                    ride.getSeatsAvailable(),
                    ride.getVehicleNumber(),
                    driverName,
                    driverMobile,
                    ride.getStatus()
            );

            list.add(dto);
        }

        return list;
    }

    // Get rides created by driver
    public List<Ride> getDriverRides(String email) {
        return rideRepository.findByDriverEmail(email);
    }

    // Cancel Ride
    public Ride cancelRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId).orElse(null);

        if (ride != null) {
            ride.setStatus("CANCELLED");
            return rideRepository.save(ride);
        }

        return null;
    }
}