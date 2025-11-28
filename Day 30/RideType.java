package com.javapractice.invoiceService;

public enum RideType {
    NORMAL(10,1,5),
    PREMIUM(15,2,20);

    private final double costPerKm;
    private final int costPerMin;
    private final double minFare;

    RideType(double costPerKm, int costPerMin, double minFare) {
        this.costPerKm = costPerKm;
        this.costPerMin = costPerMin;
        this.minFare = minFare;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public int getCostPerMin() {
        return costPerMin;
    }

    public double getMinFare() {
        return minFare;
    }
}
