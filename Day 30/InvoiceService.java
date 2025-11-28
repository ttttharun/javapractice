package com.javapractice.invoiceService;

import java.util.List;

public class InvoiceService {
    private final InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
    private final RideRepository repository;

    public InvoiceService(RideRepository repository) {
        this.repository = repository;
    }

    public InvoiceSummary returnInvoice(int userId) {
        if (!repository.getUserRides().containsKey(userId)) return null;
        List<Ride> rides = repository.getRides(userId);
        return invoiceGenerator.calculateFare(rides);
    }

}
