package com.campusride.backend.controller;

import com.campusride.backend.entity.Vehicle;
import com.campusride.backend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin(origins = "*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register")
    public ResponseEntity<?> registerVehicle(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String mobile,
            @RequestParam String gender,
            @RequestParam String vehicleModel,
            @RequestParam String vehicleNumber,
            @RequestParam MultipartFile collegeId,
            @RequestParam MultipartFile license,
            @RequestParam MultipartFile rc
    ) throws IOException {
    		

        Vehicle vehicle = new Vehicle();
        vehicle.setName(name);
        vehicle.setEmail(email);
        vehicle.setMobile(mobile);
        vehicle.setGender(gender);
        vehicle.setVehicleModel(vehicleModel);
        vehicle.setVehicleNumber(vehicleNumber);

        vehicleService.registerVehicle(vehicle, collegeId, license, rc, email);

        return ResponseEntity.ok("Vehicle submitted for approval");
    }
}
