package com.campusride.backend.repository;

import com.campusride.backend.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findByPassengerEmail(String email);

    List<Ride> findByDriverEmail(String email);

    @Query("""
        SELECT r FROM Ride r
        WHERE r.status = 'ACTIVE'
        AND (
                r.rideDate > :today
                OR
                (r.rideDate = :today AND r.rideTime >= :currentTime)
            )
        ORDER BY r.rideDate, r.rideTime
    """)
    List<Ride> findAvailableRides(@Param("today") String today,
                                  @Param("currentTime") String currentTime);
}