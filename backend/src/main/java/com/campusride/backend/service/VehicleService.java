package com.campusride.backend.service;

import com.campusride.backend.entity.Vehicle;
import com.campusride.backend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    private final String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;

    // Register vehicle with files
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

        return "uploads/" + folder + "/" + fileName;
    }


    public Vehicle registerVehicle(
            Vehicle vehicle,
            MultipartFile collegeId,
            MultipartFile license,
            MultipartFile rc,
            String email) throws IOException {
    	
    		List<Vehicle> vehicles =
                vehicleRepository.findByEmailAndStatus(email, "APPROVED");
    		if(vehicles.size()>0) {
    			throw new RuntimeException("You already have an approved vehicle.");
    		}

        String collegePath = saveFile(collegeId, "college");
        String licensePath = saveFile(license, "license");
        String rcPath = saveFile(rc, "rc");

        vehicle.setCollegeIdImage(collegePath);
        vehicle.setLicenseImage(licensePath);
        vehicle.setRcImage(rcPath);
        vehicle.setStatus("PENDING");

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getPendingVehicles() {
        return vehicleRepository.findByStatus("PENDING");
    }

    public Vehicle approveVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setStatus("APPROVED");
        return vehicleRepository.save(vehicle);
    }

    public Vehicle rejectVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setStatus("REJECTED");
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getApprovedVehicleByEmail(String email) {
        List<Vehicle> vehicles =
                vehicleRepository.findByEmailAndStatus(email, "APPROVED");

        if (vehicles.isEmpty()) {
            throw new RuntimeException("No approved vehicle found");
        }

        return vehicles.get(0);
    }

    public Vehicle getVehicleByNumber(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }
}