package com.campusride.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusride.backend.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByStatus(String status);

    // Get approved vehicle for ride creation
    Optional<Vehicle> findByEmailAndStatus(String email, String status);

    // ✅ NEW: Get all vehicles of a user (for profile page)
    List<Vehicle> findByEmail(String email);

    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
}