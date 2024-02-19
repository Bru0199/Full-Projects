package com.upg.logisticsmgmt.pojo;

public class Booking {
    private String from;
    private String to;
    private String weight;
    private String weightUnit;
    private String driverId;
    private String createdBy;
    private String createdOn;
    private String id;

    public Booking() {
    }

    public Booking(String from, String to, String weight, String weightUnit) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.weightUnit = weightUnit;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", weight='" + weight + '\'' +
                ", weightUnit='" + weightUnit + '\'' +
                ", driverId='" + driverId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
