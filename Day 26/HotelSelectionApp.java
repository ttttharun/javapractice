package com.javapractice.hotelManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HotelSelectionApp {
    public static void main(String[] args) {

        HotelService hotelService = new HotelService();
        hotelService.addHotel(new Hotel("LakeWood", HotelRating.THREE_STAR, 110, 80, 90, 80));
        hotelService.addHotel(new Hotel("BridgeWood", HotelRating.FOUR_STAR, 160, 110, 60, 50));
        hotelService.addHotel(new Hotel("RidgeWood", HotelRating.FIVE_STAR, 220, 100, 150, 40));

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("\n========= HOTEL RESERVATION SYSTEM =========");
            System.out.println("1. Find Cheapest Hotel");
            System.out.println("2. Find Cheapest Hotel With Minimum Rating");
            System.out.println("3. Show All Hotels");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter start date (dd-MM-yyyy): ");
                    LocalDate start = LocalDate.parse(sc.nextLine(), fmt);

                    System.out.print("Enter end date (dd-MM-yyyy): ");
                    LocalDate end = LocalDate.parse(sc.nextLine(), fmt);

                    System.out.print("Customer Type (REGULAR / REWARD): ");
                    CustomerType type = CustomerType.valueOf(sc.nextLine().toUpperCase());

                    String result = hotelService.findCheapest(start, end, type);
                    System.out.println("\nCheapest Hotel → " + result);
                }

                case 2 -> {
                    System.out.print("Enter start date (dd-MM-yyyy): ");
                    LocalDate start = LocalDate.parse(sc.nextLine(), fmt);

                    System.out.print("Enter end date (dd-MM-yyyy): ");
                    LocalDate end = LocalDate.parse(sc.nextLine(), fmt);

                    System.out.print("Customer Type (REGULAR / REWARD): ");
                    CustomerType type = CustomerType.valueOf(sc.nextLine().toUpperCase());

                    System.out.print("Minimum Rating (1–5): ");
                    int rating = Integer.parseInt(sc.nextLine());
                    HotelRating hotelRating = HotelRating.fromValue(rating);

                    String result =
                            hotelService.findBestCheapest(start, end, type, hotelRating);

                    System.out.println("\nCheapest Hotel (With Rating Filter) → " + result);
                }

                case 3 -> {
                    System.out.println("\nAvailable Hotels:");
                    hotelService.getHotels().forEach(System.out::println);
                }

                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }
}

