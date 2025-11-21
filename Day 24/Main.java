package bridgelabz;

public class Main {
    public static void main(String[] args) {

        AddressBookManager manager = new AddressBookManager();

        // Create Address Books
        manager.addAddressBook("Friends");
        manager.addAddressBook("Family");

        // Add contacts
        manager.addContactToBook(
                "Friends",
                new Contact("Alice", "Johnson", "NYC", "NY", "1234567890", "alice@mail.com")
        );

        manager.addContactToBook(
                "Friends",
                new Contact("Bob", "Martin", "NYC", "NY", "9998887777", "bob@mail.com")
        );

        // Duplicate entry (same first + last name)
        boolean duplicateAdded = manager.addContactToBook(
                "Friends",
                new Contact("Alice", "Johnson", "LA", "CA", "1112223333", "alice2@mail.com")
        );

        System.out.println("Duplicate Contact Added? " + duplicateAdded); // should be false

        // Add contacts to Family book
        manager.addContactToBook(
                "Family",
                new Contact("Charlie", "Dawson", "LA", "CA", "2223334444", "charlie@mail.com")
        );

        manager.addContactToBook(
                "Family",
                new Contact("David", "Miller", "NYC", "NY", "5556667777", "david@mail.com")
        );

        // --- SEARCHING ---
        System.out.println("\nSearching persons in city 'NYC':");
        manager.searchByCity("NYC").forEach(System.out::println);

        System.out.println("\nSearching persons in state 'CA':");
        manager.searchByState("CA").forEach(System.out::println);

        // --- COUNT BY CITY ---
        System.out.println("\nCount of people in city 'NYC': "
                + manager.countByCity("NYC"));

        // --- COUNT BY STATE ---
        System.out.println("Count of people in state 'CA': "
                + manager.countByState("CA"));
    }
}
