package com.campusride.backend.dto;

public class RideRequestDTO {

    private Long id;
    private String source;
    private String destination;
    private String rideDate;
    private String rideTime;
    private int seatsAvailable;
    private String vehicleNumber;
    private String driverName;
    private String driverMobile;

    public RideRequestDTO(Long id, String source, String destination,
                          String rideDate, String rideTime,
                          int seatsAvailable, String vehicleNumber,
                          String driverName, String driverMobile) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.rideDate = rideDate;
        this.rideTime = rideTime;
        this.seatsAvailable = seatsAvailable;
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
        this.driverMobile = driverMobile;
    }

    public Long getId() { return id; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getRideDate() { return rideDate; }
    public String getRideTime() { return rideTime; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getDriverName() { return driverName; }
    public String getDriverMobile() { return driverMobile; }
}