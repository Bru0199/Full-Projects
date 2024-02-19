package com.upg.logisticsmgmt.pojo;

public class Vehicle {

    private String truckNumber;
    private String permitId;
    private String driverId;
    private  String location;

    public Vehicle() {
    }

    public Vehicle(String truckNumber, String permitId, String driverId) {
        this.truckNumber = truckNumber;
        this.permitId = permitId;
        this.driverId = driverId;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getPermitId() {
        return permitId;
    }

    public void setPermitId(String permitId) {
        this.permitId = permitId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "truckNumber='" + truckNumber + '\'' +
                ", permidId='" + permitId + '\'' +
                ", driverId='" + driverId + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
