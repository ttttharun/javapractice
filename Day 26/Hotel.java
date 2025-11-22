package com.javapractice.hotelManagement;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Hotel {
    private final String hotelName;
    private HotelRating rating;
    private int weekdayRateRegularCustomer;
    private int weekdayRateRewardCustomer;
    private int weekendRateRegularCustomer;
    private int weekendRateRewardCustomer;

    public Hotel (
            String hotelName,
            HotelRating rating,
            int weekdayRateRegularCustomer,
            int weekdayRateRewardCustomer,
            int weekendRateRegularCustomer,
            int weekendRateRewardCustomer
    ) {
        this.hotelName = hotelName;
        this.rating = rating;
        this.weekdayRateRegularCustomer = weekdayRateRegularCustomer;
        this.weekdayRateRewardCustomer = weekdayRateRewardCustomer;
        this.weekendRateRegularCustomer = weekendRateRegularCustomer;
        this.weekendRateRewardCustomer = weekendRateRewardCustomer;
    }

    public boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public int calculatePrice(LocalDate start, LocalDate end, CustomerType customerType) {
        int total = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            if (customerType == CustomerType.REGULAR) {
                total += isWeekend(date) ? weekendRateRegularCustomer : weekdayRateRegularCustomer;
            } else {
                total += isWeekend(date) ? weekendRateRewardCustomer : weekdayRateRewardCustomer;
            }
        }
        return total;
    }

    public String getHotelName() {
        return hotelName;
    }

    public HotelRating getRating() {
        return rating;
    }

    public void setRating(HotelRating rating) {
        this.rating = rating;
    }

    public int getWeekdayRateRegularCustomer() {
        return weekdayRateRegularCustomer;
    }

    public void setWeekdayRateRegularCustomer(int weekdayRateRegularCustomer) {
        this.weekdayRateRegularCustomer = weekdayRateRegularCustomer;
    }

    public int getWeekdayRateRewardCustomer() {
        return weekdayRateRewardCustomer;
    }

    public void setWeekdayRateRewardCustomer(int weekdayRateRewardCustomer) {
        this.weekdayRateRewardCustomer = weekdayRateRewardCustomer;
    }

    public int getWeekendRateRegularCustomer() {
        return weekendRateRegularCustomer;
    }

    public void setWeekendRateRegularCustomer(int weekendRateRegularCustomer) {
        this.weekendRateRegularCustomer = weekendRateRegularCustomer;
    }

    public int getWeekendRateRewardCustomer() {
        return weekendRateRewardCustomer;
    }

    public void setWeekendRateRewardCustomer(int weekendRateRewardCustomer) {
        this.weekendRateRewardCustomer = weekendRateRewardCustomer;
    }
}
