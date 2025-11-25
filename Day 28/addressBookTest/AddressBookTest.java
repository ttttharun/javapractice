package com.addressBookTest;

import com.javapractice.addressBook.AddressBook;
import com.javapractice.addressBook.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Scanner;

public class AddressBookTest {

    private AddressBook addressBook;

    @Before
    public void setUp() {
        addressBook = new AddressBook("TestBook");
    }

    // ------------------------------ ADD CONTACT -----------------------------

    @Test
    public void givenValidContact_whenAdded_thenShouldIncreaseListSize() {
        // Arrange
        Contact c = new Contact("John", "Doe", "Addr", "City", "State", 11111, 1111111111L, "john@example.com");

        // Act
        addressBook.addContact(c);

        // Assert
        Assert.assertEquals(1, addressBook.getContacts().size());
    }

    @Test
    public void givenDuplicateContact_whenAdded_thenShouldNotAddAgain() {
        // Arrange
        Contact c = new Contact("John", "Doe", "Addr", "City", "State", 11111, 1111111111L, "john@example.com");

        // Act
        addressBook.addContact(c);
        addressBook.addContact(c);

        // Assert
        Assert.assertEquals(1, addressBook.getContacts().size());
    }

    // ------------------------------ UPDATE CONTACT -----------------------------

    @Test
    public void givenExistingContact_whenUpdated_thenShouldChangeItsFields() {
        // Arrange
        Contact old = new Contact("John", "Doe", "Old", "OldCity", "OldState", 11111, 1111L, "old@example.com");
        addressBook.addContact(old);

        String input =
                """
                        Johnny
                        Doe
                        NewAddr
                        NewCity
                        NewState
                        22222
                        2222222222
                        new@example.com
                        """;

        Scanner sc = new Scanner(input);

        // Act
        addressBook.updateContact("John", sc);

        // Assert
        Contact updated = addressBook.getContacts().get(0);
        Assert.assertEquals("Johnny", updated.getFirstName());
        Assert.assertEquals("NewAddr", updated.getAddress());
        Assert.assertEquals("NewCity", updated.getCity());
        Assert.assertEquals(22222, updated.getZipCode());
        Assert.assertEquals(2222222222L, updated.getPhoneNumber());
    }

    // ------------------------------ DELETE CONTACT -----------------------------

    @Test
    public void givenExistingContact_whenDeleted_thenShouldRemoveIt() {
        // Arrange
        Contact c = new Contact("John", "Doe", "Addr", "City", "State", 11111, 111L, "john@example.com");
        addressBook.addContact(c);

        // Act
        addressBook.deleteContact("John");

        // Assert
        Assert.assertTrue(addressBook.getContacts().isEmpty());
    }

    // ------------------------------ SEARCH BY CITY -----------------------------

    @Test
    public void givenContactsInCity_whenSearchByCity_thenShouldReturnCorrectCount() {
        // Arrange
        addressBook.addContact(new Contact("A","X","A1","NYC","NY",1,1,"a@x.com"));
        addressBook.addContact(new Contact("B","Y","A2","NYC","NY",2,2,"b@y.com"));
        addressBook.addContact(new Contact("C","Z","A3","LA","CA",3,3,"c@z.com"));

        // Act
        List<Contact> cityList = addressBook.searchByCity("NYC");

        // Assert
        Assert.assertEquals(2, cityList.size());
    }

    // ------------------------------ SORTING -----------------------------

    @Test
    public void givenUnsortedCities_whenSorted_thenOrderShouldBeAscending() {
        // Arrange
        addressBook.addContact(new Contact("A","X","A1","Mumbai","MH",1,1,"a@x.com"));
        addressBook.addContact(new Contact("B","Y","A2","chennai","TN",2,2,"b@y.com"));
        addressBook.addContact(new Contact("C","Z","A3","Bangalore","KA",3,3,"c@z.com"));

        // Act
        addressBook.sortByCity();

        // Assert
        Assert.assertEquals("Bangalore", addressBook.getContacts().get(0).getCity());
        Assert.assertEquals("chennai", addressBook.getContacts().get(1).getCity());
        Assert.assertEquals("Mumbai", addressBook.getContacts().get(2).getCity());
    }

    // ------------------------------ ADD MULTIPLE -----------------------------

    @Test
    public void givenTwoInputs_whenAddMultipleContact_thenShouldAddTwoContacts() {
        // Arrange
        String input =
                """
                        John
                        Doe
                        Addr1
                        City1
                        State1
                        11111
                        1111000000
                        john@example.com
                        yes
                        Jane
                        Smith
                        Addr2
                        City2
                        State2
                        22222
                        2222000000
                        jane@example.com
                        no
                        """;

        Scanner sc = new Scanner(input);

        // Act
        addressBook.addMultipleContact(sc);

        // Assert
        Assert.assertEquals(2, addressBook.getContacts().size());
        Assert.assertEquals("John", addressBook.getContacts().get(0).getFirstName());
        Assert.assertEquals("Jane", addressBook.getContacts().get(1).getFirstName());
    }
}


