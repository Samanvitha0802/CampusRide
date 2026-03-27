package com.campusride.backend.service;

import com.campusride.backend.entity.Ride;
import com.campusride.backend.dto.RideRequestDTO;
import com.campusride.backend.entity.Vehicle;
import com.campusride.backend.repository.RideRepository;
import com.campusride.backend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    // Create ride
    public Ride createRide(Ride ride) {
        Vehicle vehicle = vehicleService
                .getApprovedVehicleByEmail(ride.getDriverEmail());

        if (vehicle != null) {
            ride.setVehicleNumber(vehicle.getVehicleNumber());
        }

        return rideRepository.save(ride);
    }

    // Get all rides with driver details
    public List<RideRequestDTO> getAllRideDetails() {

        List<Ride> rides = rideRepository.findAll();
        List<RideRequestDTO> list = new ArrayList<>();

        for (Ride ride : rides) {

            String name = "Unknown";
            String mobile = "Not Available";

            if (ride.getVehicleNumber() != null) {

                List<Vehicle> vehicles =
                        vehicleRepository.findByVehicleNumber(ride.getVehicleNumber());

                Vehicle vehicle = vehicles.isEmpty() ? null : vehicles.get(0);

                if (vehicle != null) {
                    name = vehicle.getName();
                    mobile = vehicle.getMobile();
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
                    name,
                    mobile
            );

            list.add(dto);
        }

        return list;
    }
}