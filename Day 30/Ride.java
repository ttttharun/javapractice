package com.javapractice.invoiceService;

public class Ride {
    private final double distance;
    private final int time;
    private final RideType rideType;

    public Ride(double distance, int time, RideType rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;
    }

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public RideType getRideType() {
        return rideType;
    }
}
