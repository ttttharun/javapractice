package com.javapractice.invoiceService;

import java.util.List;

public class InvoiceGenerator {

    public double calculateFare(RideType rideType, double distance, int time) {
        double fare =  distance * rideType.getCostPerKm() + time * rideType.getCostPerMin();
        return Math.max(fare, rideType.getMinFare());
    }

    public InvoiceSummary calculateFare(List<Ride> rides) {
        double totalFare = rides.stream()
                .mapToDouble(r -> calculateFare(r.getRideType(), r.getDistance(), r.getTime()))
                .sum();
        return new InvoiceSummary(rides.size(), totalFare);
    }


}
