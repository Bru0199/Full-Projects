package com.upg.logisticsmgmt.pojo;

import com.upg.logisticsmgmt.pojo.Booking;

public class BookingWithLocation extends Booking {

    private String currentLocation;

    public BookingWithLocation() {
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "BookingWithLocation{" +
                "currentLocation='" + currentLocation + '\'' +
                '}';
    }
}
