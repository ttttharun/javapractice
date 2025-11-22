package com.javapractice.hotelManagement;

public enum HotelRating {
    ONE_STAR(1), TWO_STAR(2), THREE_STAR(3), FOUR_STAR(4), FIVE_STAR(5);

    private final int value;
    HotelRating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static HotelRating fromValue(int v) {
        return switch (v) {
            case 1 -> ONE_STAR;
            case 2 -> TWO_STAR;
            case 3 -> THREE_STAR;
            case 4 -> FOUR_STAR;
            case 5 -> FIVE_STAR;
            default -> throw new IllegalArgumentException("Invalid rating: " + v);
        };
    }
}
