package com.campusride.backend.repository;

import com.campusride.backend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByStatus(String status);

    List<Vehicle> findByEmailAndStatus(String email, String status);

    List<Vehicle> findByVehicleNumber(String vehicleNumber);

    // ADD THIS METHOD
    List<Vehicle> findByEmail(String email);
}