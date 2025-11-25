package com.javapractice.addressBook;

import java.util.*;

public class AddressBook {
    private final String name;
    private final List<Contact> contacts = new ArrayList<>();

    public AddressBook(String name) {
        this.name = name;
    }

    // getting input from console to create a Contact object
    public Contact input(Scanner consoleInputScanner) {
        System.out.print("Enter first name: ");
        String firstName = consoleInputScanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = consoleInputScanner.nextLine();
        System.out.print("Enter address: ");
        String address = consoleInputScanner.nextLine();
        System.out.print("Enter city: ");
        String city = consoleInputScanner.nextLine();
        System.out.print("Enter state: ");
        String state = consoleInputScanner.nextLine();
        System.out.print("Enter zip: ");
        int zip = consoleInputScanner.nextInt();
        consoleInputScanner.nextLine();
        System.out.print("Enter mobile number: ");
        long mobileNumber = consoleInputScanner.nextLong();
        consoleInputScanner.nextLine();
        System.out.print("Enter email: ");
        String email = consoleInputScanner.nextLine();
        return new Contact(firstName, lastName, address, city, state, zip, mobileNumber, email);
    }

    // adding a single contact
    public void addContact(Contact contact) {
        if (contacts.contains(contact)) {
            System.out.println("Contact is already in the address book.");
            return;
        }

        contacts.add(contact);
        System.out.println("Contact added successfully!");
    }

    // add multiple contacts
    public void addMultipleContact(Scanner scanner) {
        boolean stop = false;
        while (!stop) {
            Contact newContact = input(scanner);
            addContact(newContact);
            System.out.print("Do you want to add another contact? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes"))
                stop = true;
        }
        System.out.println("Contacts added successfully!");
    }

    // update a contact
    public void updateContact(String firstName, Scanner inputScanner) {
        Contact updatable = contacts.stream()
                .filter(contact -> contact.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
        if (updatable == null) {
            System.out.println("Cannot find contact with the name " + firstName);
            return;
        }
        System.out.println("Updating contact: " + updatable);
        Contact updated = input(inputScanner);
        int index = contacts.indexOf(updatable);
        contacts.set(index, updated);
        System.out.println("Contact updated successfully.");
    }

    // delete a contact
    public void deleteContact(String firstName) {
        boolean removed = contacts.removeIf(c -> c.getFirstName().equalsIgnoreCase(firstName));
        if (!removed)
            System.out.println("Cannot find contact with the name " + firstName);
        else
            System.out.println("Contact successfully deleted.");
    }

    // search by city
    public List<Contact> searchByCity(String city) {
        return contacts.stream()
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .toList();
    }

    // print em
    public void printContactsByCity(String city) {
        searchByCity(city).forEach(System.out::println);
    }

    // count em
    public int countByCity(String city) {
        return searchByCity(city).size();
    }

    // sort em
    public void sortByCity() {
        contacts.sort(Comparator.comparing(Contact::getCity, String.CASE_INSENSITIVE_ORDER));
    }

    // search by state
    public List<Contact> searchByState(String state) {
        return contacts.stream()
                .filter(c -> c.getState().equalsIgnoreCase(state))
                .toList();
    }

    // print em
    public void printContactsByState(String state) {
        searchByState(state).forEach(System.out::println);
    }

    // count em
    public int countByState(String state) {
        return searchByState(state).size();
    }

    // sort em
    public void sortByState() {
        contacts.sort(Comparator.comparing(Contact::getState, String.CASE_INSENSITIVE_ORDER));
    }

    // sort by zip code
    public void sortByZip() {
        contacts.sort(Comparator.comparingInt(Contact::getZipCode));
    }

    // print all the contacts in the AddressBook
    public void printContactList() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to print.");
            return;
        }
        contacts.forEach(System.out::println);
    }

    public void sortContactsByName() {
        contacts.sort(Comparator.comparing(Contact::getFirstName, String.CASE_INSENSITIVE_ORDER));
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }

    public String getName() {
        return name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
