package com.addressBookTest;

import com.javapractice.addressBook.AddressBook;
import com.javapractice.addressBook.AddressBookManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookManagerTest {

    private AddressBookManager manager;

    @Before
    public void setUp() {
        manager = new AddressBookManager();
    }

    @Test
    public void givenNewName_whenAddAddressBook_thenShouldCreateAndStoreIt() {
        // Act
        manager.addAddressBook("Friends");
        AddressBook ab = manager.getAddressBook("Friends");

        // Assert
        Assert.assertNotNull(ab);
        Assert.assertEquals("Friends", ab.getName());
    }

    @Test
    public void givenDuplicateName_whenAddAddressBook_thenShouldNotOverrideExisting() {
        // Arrange
        manager.addAddressBook("Friends");
        AddressBook firstRef = manager.getAddressBook("Friends");

        // Act
        manager.addAddressBook("Friends"); // duplicate
        AddressBook secondRef = manager.getAddressBook("Friends");

        // Assert: same object reference, nothing replaced
        Assert.assertSame(firstRef, secondRef);
    }
}

