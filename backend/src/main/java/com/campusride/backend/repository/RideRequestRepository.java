package com.campusride.backend.repository;

import com.campusride.backend.entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {

    List<RideRequest> findByPassengerEmail(String passengerEmail);
}