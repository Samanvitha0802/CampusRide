package com.campusride.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.campusride.backend.entity.Vehicle;
import com.campusride.backend.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    private final String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;


    private String saveFile(MultipartFile file, String folder) throws IOException {

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String folderPath = uploadDir + folder + File.separator;

        // Create folder if it doesn't exist
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = folderPath + fileName;
        File destination = new File(filePath);

        file.transferTo(destination);

        return filePath;
    }


    public Vehicle registerVehicle(
            Vehicle vehicle,
            MultipartFile collegeId,
            MultipartFile license,
            MultipartFile rc) throws IOException {

        String collegePath = saveFile(collegeId, "college");
        String licensePath = saveFile(license, "license");
        String rcPath = saveFile(rc, "rc");

        vehicle.setCollegeIdImage(collegePath);
        vehicle.setLicenseImage(licensePath);
        vehicle.setRcImage(rcPath);
        vehicle.setStatus("PENDING");

        return vehicleRepository.save(vehicle);
    }
    
 // Get all pending vehicles
    public List<Vehicle> getPendingVehicles() {
        return vehicleRepository.findByStatus("PENDING");
    }

    // Approve vehicle
    public Vehicle approveVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setStatus("APPROVED");
        return vehicleRepository.save(vehicle);
    }

    // Reject vehicle
    public Vehicle rejectVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setStatus("REJECTED");
        return vehicleRepository.save(vehicle);
    }
    
    // Check if user has approved vehicle
    public Vehicle getApprovedVehicleByEmail(String email) {
        return vehicleRepository
                .findByEmailAndStatus(email, "APPROVED")
                .orElseThrow(() -> new RuntimeException("Vehicle not verified by admin"));
    }
}
