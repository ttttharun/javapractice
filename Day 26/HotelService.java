package com.javapractice.hotelManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HotelService {
    private final List<Hotel> hotels;

    public HotelService() {
        hotels = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public String findCheapest(LocalDate start, LocalDate end, CustomerType customerType) {
        Hotel cheapestHotel =  hotels.stream()
                .min(Comparator.comparingInt(hotel ->
                        hotel.calculatePrice(start, end, customerType)
                ))
                .orElse(null);
        assert cheapestHotel != null;
        return cheapestHotel.getHotelName() + ": " + cheapestHotel.calculatePrice(start, end, customerType);
    }

    public String findBestCheapest(LocalDate start,
                                   LocalDate end,
                                   CustomerType customerType,
                                   HotelRating minimumRating) {

        return hotels.stream()
                .filter(hotel -> hotel.getRating().ordinal() >= minimumRating.ordinal())
                .min(Comparator.comparingInt(hotel -> hotel.calculatePrice(start, end, customerType)))
                .map(hotel -> hotel.getHotelName() + ": " +
                        hotel.calculatePrice(start, end, customerType))
                .orElse("No hotel found with rating " + minimumRating);
    }

    public List<Hotel> getHotels() {
        return hotels;
    }
}
