package com.campusride.backend.repository;

import com.campusride.backend.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {

    // Get all rides offered by a user (driver)
    List<Ride> findByDriverEmail(String email);

}