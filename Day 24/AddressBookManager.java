package bridgelabz;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookManager {

    private Map<String, AddressBook> addressBookMap = new HashMap<>();

    public void addAddressBook(String name) {
        addressBookMap.putIfAbsent(name, new AddressBook());
    }

    public boolean addContactToBook(String bookName, Contact contact) {
        AddressBook book = addressBookMap.get(bookName);
        if (book == null) return false;
        return book.addContact(contact);
    }

    // Ability to ensure no duplicate entry is handled in AddressBook.addContact()

    // Search Person in a City across books
    public List<Contact> searchByCity(String city) {
        return addressBookMap.values()
                .stream()
                .flatMap(book -> book.getContacts().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    // Search Person in a State across books
    public List<Contact> searchByState(String state) {
        return addressBookMap.values()
                .stream()
                .flatMap(book -> book.getContacts().stream())
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    // View persons by City
    public Map<String, List<Contact>> viewByCity() {
        return addressBookMap.values()
                .stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getCity));
    }

    // View persons by State
    public Map<String, List<Contact>> viewByState() {
        return addressBookMap.values()
                .stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getState));
    }

    // Count by City
    public long countByCity(String city) {
        return addressBookMap.values()
                .stream()
                .flatMap(book -> book.getContacts().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .count();
    }

    // Count by State
    public long countByState(String state) {
        return addressBookMap.values()
                .stream()
                .flatMap(book -> book.getContacts().stream())
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .count();
    }
}
