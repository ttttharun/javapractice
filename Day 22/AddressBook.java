package bridgelabz;

import java.util.HashSet;
import java.util.Set;

public class AddressBook {

    private Set<Contact> contacts = new HashSet<>();

    // Add a new contact (prevents duplicate person)
    public boolean addContact(Contact contact) {
        return contacts.add(contact); // HashSet + equals() + hashCode() prevent duplicates
    }

    // Return all contacts in this address book
    public Set<Contact> getContacts() {
        return contacts;
    }
}
