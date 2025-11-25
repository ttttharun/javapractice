package com.addressBookTest;

import com.javapractice.addressBook.AddressBookFileIOService;
import com.javapractice.addressBook.Contact;
import org.junit.*;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddressBookFileIOServiceTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private AddressBookFileIOService fileService;
    private List<Contact> sampleContacts;

    @Before
    public void setUp() throws IOException {
        fileService = new AddressBookFileIOService();

        sampleContacts = Arrays.asList(
                new Contact("John", "Doe", "Addr1", "City1", "State1", 11111, 1111111111L, "john@example.com"),
                new Contact("Jane", "Smith", "Addr2", "City2", "State2", 22222, 2222222222L, "jane@example.com")
        );

        // Redirect FILE_NAME to a temp file to avoid touching real filesystem
        File tempFile = tempFolder.newFile("address_book-file.txt");
        AddressBookFileIOService.FILE_NAME = tempFile.getAbsolutePath();
    }

    @Test
    public void givenContacts_whenWriteAndReadData_thenShouldReturnSameContacts() {
        // Arrange
        fileService.writeData(sampleContacts);

        // Act
        List<Contact> readContacts = fileService.readData();

        // Assert
        Assert.assertEquals(sampleContacts.size(), readContacts.size());
        Assert.assertTrue(readContacts.containsAll(sampleContacts));
    }

    @Test
    public void givenContactToStringLine_whenParseContact_thenShouldCreateEquivalentContact() {
        // Arrange
        Contact original = sampleContacts.get(0);
        String line = original.toString();

        // Act
        Contact parsed = fileService.parseContact(line);

        // Assert
        Assert.assertEquals(original, parsed);
    }

    @Test
    public void givenWrittenData_whenCountEntries_thenShouldReturnNumberOfLines() {
        // Arrange
        fileService.writeData(sampleContacts);

        // Act
        long count = fileService.countEntries();

        // Assert
        Assert.assertEquals(sampleContacts.size(), count);
    }

    @Test
    public void givenContacts_whenWriteAndReadCSV_thenShouldMatchData() throws IOException {
        // Arrange
        File csvFile = tempFolder.newFile("contacts.csv");

        // Act
        fileService.writeCSV(sampleContacts, csvFile.getAbsolutePath());
        List<Contact> readFromCsv = fileService.readCSV(csvFile.getAbsolutePath());

        // Assert
        Assert.assertEquals(sampleContacts.size(), readFromCsv.size());
        Assert.assertTrue(readFromCsv.containsAll(sampleContacts));
    }

    @Test
    public void givenContacts_whenWriteAndReadJSON_thenShouldMatchData() throws IOException {
        // Arrange
        File jsonFile = tempFolder.newFile("contacts.json");

        // Act
        fileService.writeJSON(sampleContacts, jsonFile.getAbsolutePath());
        List<Contact> readFromJson = fileService.readJSON(jsonFile.getAbsolutePath());

        // Assert
        Assert.assertEquals(sampleContacts.size(), readFromJson.size());
        Assert.assertTrue(readFromJson.containsAll(sampleContacts));
    }
}
