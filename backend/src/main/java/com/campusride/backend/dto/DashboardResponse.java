package com.campusride.backend.dto;

import java.util.List;

public class DashboardResponse {

    public String name;
    public String email;
    public String phone;

    public List<VehicleDTO> vehicles;
    public List<RideDTO> ridesOffered;
    public List<RideDTO> ridesUsed;
}