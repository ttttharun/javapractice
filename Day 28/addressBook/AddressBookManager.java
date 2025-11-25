package com.javapractice.addressBook;

import java.util.HashMap;
import java.util.Map;

public class AddressBookManager {
    private final Map<String, AddressBook> addressBooks = new HashMap<>();

    public void addAddressBook(String name) {
        if (addressBooks.containsKey(name)) {
            System.out.println("Address Book already exists.");
            return;
        }
        addressBooks.put(name, new AddressBook(name));
        System.out.println("Address Book added successfully.");
    }

    public AddressBook getAddressBook(String name) {
        return addressBooks.get(name);
    }

    public void printAddressBooks() {
        addressBooks.keySet().forEach(System.out::println);
    }
}
