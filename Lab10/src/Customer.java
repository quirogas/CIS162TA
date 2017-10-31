/**
 * Created by unclear on 10/30/17.
 */
public class Customer {
    String firstName, lastName, eMail;

    public Customer(String firstName, String lastName, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ": " + eMail;
    }
}
