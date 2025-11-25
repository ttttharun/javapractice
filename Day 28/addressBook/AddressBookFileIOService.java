package com.javapractice.addressBook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookFileIOService {
    public static String FILE_NAME = "address_book-file.txt";

    // write the file into the FILE_NAME
    public void writeData(List<Contact> contacts) {
        StringBuffer contactBuffer = new StringBuffer();
        contacts.forEach(c -> {
            contactBuffer.append(c.toString().concat("\n"));
        });

        try {
            Files.write(Paths.get(FILE_NAME), contactBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Contact parseContact(String line) {
        // Remove "Contact{" and "}"
        line = line.replace("Contact{", "").replace("}", "");

        // Split on comma, but keep key=value pairs
        String[] parts = line.split(",(?=(?:[^']*'[^']*')*[^']*$)");

        // Extract fields
        String firstName = parts[0].split("=")[1].trim().replace("'", "");
        String lastName = parts[1].split("=")[1].trim().replace("'", "");
        String address = parts[2].split("=")[1].trim().replace("'", "");
        String city = parts[3].split("=")[1].trim().replace("'", "");
        String state = parts[4].split("=")[1].trim().replace("'", "");
        int zipCode = Integer.parseInt(parts[5].split("=")[1].trim());
        long phoneNumber = Long.parseLong(parts[6].split("=")[1].trim());
        String email = parts[7].split("=")[1].trim().replace("'", "");

        return new Contact(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
    }

    // read the file and get the list of contacts
    public List<Contact> readData() {
        List<Contact> contacts = new ArrayList<>();
        try {
            Files.lines(new File(FILE_NAME).toPath())
                    .forEach(line ->
                            contacts.add(parseContact(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // print the file's contents
    public void printData() {
        try {
            Files.lines(new File(FILE_NAME).toPath())
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // count all the entries in the file
    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(FILE_NAME).toPath())
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    // -------------------- CSV --------------------
    public void writeCSV(List<Contact> contacts, String csvFileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {

            // Header row
            String[] header = {"firstName", "lastName", "address", "city", "state", "zipCode", "phoneNumber", "email"};
            writer.writeNext(header);

            // Write each contact
            for (Contact c : contacts) {
                String[] row = {
                        c.getFirstName(),
                        c.getLastName(),
                        c.getAddress(),
                        c.getCity(),
                        c.getState(),
                        String.valueOf(c.getZipCode()),
                        String.valueOf(c.getPhoneNumber()),
                        c.getEmail()
                };
                writer.writeNext(row);
            }

            System.out.println("CSV file written successfully: " + csvFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> readCSV(String csvFileName) {
        List<Contact> contacts = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFileName))) {

            // Skip header
            reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {

                String firstName = nextLine[0];
                String lastName = nextLine[1];
                String address = nextLine[2];
                String city = nextLine[3];
                String state = nextLine[4];
                int zipCode = Integer.parseInt(nextLine[5]);
                long phoneNumber = Long.parseLong(nextLine[6]);
                String email = nextLine[7];

                contacts.add(new Contact(firstName, lastName, address, city, state, zipCode, phoneNumber, email));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contacts;
    }

    // --------------------- JSON ---------------------
    public void writeJSON(List<Contact> contacts, String jsonFileName) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();   // pretty JSON output

        try (FileWriter writer = new FileWriter(jsonFileName)) {
            gson.toJson(contacts, writer);
            System.out.println("JSON file written successfully: " + jsonFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> readJSON(String jsonFileName) {
        List<Contact> contacts = new ArrayList<>();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(jsonFileName)) {

            Type contactListType = new TypeToken<List<Contact>>() {}.getType();
            contacts = gson.fromJson(reader, contactListType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contacts;
    }

}
