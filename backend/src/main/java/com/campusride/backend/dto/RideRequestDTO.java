
package com.campusride.backend.dto;

public class RideRequestDTO {

    private Long rideId;
    private String source;
    private String destination;
    private String rideDate;
    private String rideTime;
    private int seatsAvailable;
    private String vehicleNumber;
    private String driverName;
    private String driverMobile;
    private String status;

    // Empty constructor
    public RideRequestDTO() {
    }

    // Constructor with all fields
    public RideRequestDTO(Long rideId,
                          String source,
                          String destination,
                          String rideDate,
                          String rideTime,
                          int seatsAvailable,
                          String vehicleNumber,
                          String driverName,
                          String driverMobile,
                          String status) {

        this.rideId = rideId;
        this.source = source;
        this.destination = destination;
        this.rideDate = rideDate;
        this.rideTime = rideTime;
        this.seatsAvailable = seatsAvailable;
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
        this.driverMobile = driverMobile;
        this.status = status;
    }

    public Long getRideId() { return rideId; }
    public void setRideId(Long rideId) { this.rideId = rideId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getRideDate() { return rideDate; }
    public void setRideDate(String rideDate) { this.rideDate = rideDate; }

    public String getRideTime() { return rideTime; }
    public void setRideTime(String rideTime) { this.rideTime = rideTime; }

    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getDriverMobile() { return driverMobile; }
    public void setDriverMobile(String driverMobile) { this.driverMobile = driverMobile; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
