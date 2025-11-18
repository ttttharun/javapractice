package bridgelabz;

public class Contact {
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String phone;
    private String email;

    public Contact(String firstName, String lastName, String city, String state, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact c = (Contact) o;
        return this.firstName.equalsIgnoreCase(c.firstName)
                && this.lastName.equalsIgnoreCase(c.lastName);
    }

    @Override
    public int hashCode() {
        return (firstName + lastName).toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + city + ", " + state + ")";
    }
}
