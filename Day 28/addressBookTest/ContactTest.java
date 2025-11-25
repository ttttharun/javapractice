package com.addressBookTest;

import com.javapractice.addressBook.Contact;
import org.junit.Assert;
import org.junit.Test;

public class ContactTest {

    @Test
    public void givenSameData_whenEquals_thenShouldBeTrueAndHashCodesSame() {
        // Arrange
        Contact c1 = new Contact("John", "Doe", "Addr", "City", "State", 12345, 9876543210L, "john@example.com");
        Contact c2 = new Contact("John", "Doe", "Addr", "City", "State", 12345, 9876543210L, "john@example.com");

        // Act & Assert
        Assert.assertEquals(c1, c2);
        Assert.assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void givenDifferentData_whenEquals_thenShouldBeFalse() {
        // Arrange
        Contact c1 = new Contact("John", "Doe", "Addr", "City", "State", 12345, 9876543210L, "john@example.com");
        Contact c2 = new Contact("Jane", "Doe", "Addr", "City", "State", 12345, 9876543210L, "john@example.com");

        // Act & Assert
        Assert.assertNotEquals(c1, c2);
    }

    @Test
    public void givenContact_whenToStringCalled_thenShouldContainKeyFields() {
        // Arrange
        Contact c = new Contact("John", "Doe", "Addr", "City", "State", 12345, 9876543210L, "john@example.com");

        // Act
        String str = c.toString();

        // Assert
        Assert.assertTrue(str.contains("John"));
        Assert.assertTrue(str.contains("Doe"));
        Assert.assertTrue(str.contains("john@example.com"));
    }

    @Test
    public void givenSetters_whenUsed_thenGettersShouldReturnValues() {
        // Arrange
        Contact c = new Contact();
        c.setFirstName("John");
        c.setLastName("Doe");
        c.setAddress("Addr");
        c.setCity("City");
        c.setState("State");
        c.setZipCode(12345);
        c.setPhoneNumber(9876543210L);
        c.setEmail("john@example.com");

        // Assert
        Assert.assertEquals("John", c.getFirstName());
        Assert.assertEquals("Doe", c.getLastName());
        Assert.assertEquals("Addr", c.getAddress());
        Assert.assertEquals("City", c.getCity());
        Assert.assertEquals("State", c.getState());
        Assert.assertEquals(12345, c.getZipCode());
        Assert.assertEquals(9876543210L, c.getPhoneNumber());
        Assert.assertEquals("john@example.com", c.getEmail());
    }
}

