// Address Book Manager with search and edit functionality

class AddressBookManager {
    constructor() {
        this.addressBook = new Array();
    }

    // Add a new contact
    addContact(contact) {
        this.addressBook.push(contact);
    }

    // Search by first name or last name
    findContactByName(name) {
        return this.addressBook.find(contact => 
            contact.firstName === name || contact.lastName === name
        );
    }

    // Search and edit a contact
    searchAndEdit(searchName, fieldToEdit, newValue) {
        let contact = this.findContactByName(searchName);
        if (contact != undefined) {
            try {
                contact[fieldToEdit] = newValue;
                console.log("Contact updated successfully!");
                console.log(contact.toString());
                return contact;
            } catch (error) {
                console.log("Error: " + error);
                return null;
            }
        } else {
            console.log("Contact '" + searchName + "' not found");
            return null;
        }
    }

    // Delete a contact by name
    deleteContactByName(name) {
        const index = this.addressBook.findIndex(contact => 
            contact.firstName === name || contact.lastName === name
        );
        if (index > -1) {
            this.addressBook.splice(index, 1);
            console.log("Contact deleted successfully!");
            return true;
        } else {
            console.log("Contact not found");
            return false;
        }
    }

    // Display all contacts
    displayAllContacts() {
        console.log("===== All Contacts =====");
        if (this.addressBook.length === 0) {
            console.log("No contacts found");
        } else {
            this.addressBook.forEach((contact, index) => {
                console.log((index + 1) + ". " + contact.toString());
            });
        }
    }
}

// Example usage:
const manager = new AddressBookManager();
manager.addContact(new Contact("John", "Doe", "123 Main St", "Springfield", "IL", "62701", "2175551234", "john@email.com"));
manager.addContact(new Contact("Jane", "Smith", "456 Oak Ave", "Chicago", "IL", "60601", "3125551234", "jane@email.com"));

console.log("===== Initial Contacts =====");
manager.displayAllContacts();

console.log("\n===== Search and Edit Example =====");
manager.searchAndEdit("Jane", "firstName", "Janet");

console.log("\n===== Updated Contacts =====");
manager.displayAllContacts();

console.log("\n===== Search and Edit Email =====");
manager.searchAndEdit("John", "email", "john.doe@email.com");

console.log("\n===== Final Contacts =====");
manager.displayAllContacts();
