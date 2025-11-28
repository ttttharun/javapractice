package com.javapractice.invoiceService;

import java.util.Objects;

public class InvoiceSummary {
    private final double totalFare;
    private final int numberOfRides;

    public InvoiceSummary(int numberOfRides, double totalFare) {
        this.numberOfRides = numberOfRides;
        this.totalFare = totalFare;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return Double.compare(totalFare, that.totalFare) == 0 && numberOfRides == that.numberOfRides;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalFare, numberOfRides);
    }
}
