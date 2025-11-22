package com.javapractice.hotelManagement;

@FunctionalInterface
public interface HotelFilter {
    boolean filter(Hotel hotel);
}
