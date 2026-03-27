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

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    // Register vehicle with files
    public Vehicle registerVehicle(Vehicle vehicle,
                                   MultipartFile collegeId,
                                   MultipartFile license,
                                   MultipartFile rc) throws IOException {

        File uploadFolder = new File(UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String collegeIdPath = saveFile(collegeId);
        String licensePath = saveFile(license);
        String rcPath = saveFile(rc);

        vehicle.setCollegeIdImage(collegeIdPath);
        vehicle.setLicenseImage(licensePath);
        vehicle.setRcImage(rcPath);
        vehicle.setStatus("PENDING");

        return vehicleRepository.save(vehicle);
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String filePath = UPLOAD_DIR + fileName;

        file.transferTo(new File(filePath));

        return filePath;
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