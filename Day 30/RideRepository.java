package com.javapractice.invoiceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepository {

    private final Map<Integer, List<Ride>> userRides = new HashMap<>();

    public void addRides(int userId, List<Ride> rides) {
        userRides.put(userId, rides);
    }

    public List<Ride> getRides(int userId) {
        return userRides.get(userId);
    }

    public Map<Integer, List<Ride>> getUserRides() {
        return userRides;
    }
}

