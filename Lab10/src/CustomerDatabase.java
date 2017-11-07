import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Santiago on 10/30/17.
 */
public class CustomerDatabase {
    /** Field Variable */
    ArrayList<Customer> customerList;

    /**
     *  A constructor that instantiates the  ArrayList of Customer. Do not add any customer to the ArrayList yet.
     */
    public CustomerDatabase() {
        this.customerList = new ArrayList<>();
    }

    /**
     * If found, return the Customer that matches the provided first name AND last name. If not found, return null.
     * The search should not be case sensitive… Hint: use the equalsIgnoreCase method to do your string comparisons.
     * @param firstName
     * @param lastName
     * @return
     */
    public Customer findCustomer(String firstName, String lastName) {
        return customerList.stream().filter(customer ->
                customer.firstName.equalsIgnoreCase(firstName) && customer.lastName.equalsIgnoreCase(lastName))
                    .findFirst().orElse(null);
    }

    /**
     * Returns all Customer records whose email  contains a specific email domain. For example, if domain is @google,
     * you should return an ArrayList of all the Customers that contain @google in the email.  Hint: use the contains
     * method of the String class.
     * @param domain
     * @return
     */
    public ArrayList<Customer> findCustomersWithSameEmailDomain(String domain) {
        ArrayList<Customer> resulstList = customerList.stream().filter(customer ->
                customer.eMail.contains(domain)).collect(Collectors.toCollection(ArrayList::new));
        return resulstList;
    }

    /**
     * Return the complete ArrayList of Customers. This is a single line of code.
     * @return
     */
    public ArrayList<Customer> getDB() {
        return customerList;
    }

    /**
     * Return the number of customers in the  ArrayList of Customers.  This is single line of code.
     * @return
     */
    public int getNumberCustomers () {
    return customerList.size();
    }

    /**
     * Open the provided file and read all customer data. Read data one element at a time. Repeatedly, instantiate a
     * new Customer and add it into the ArrayList.
     * @param fileName
     */
    public void readCustomerData(String fileName) {
        //read file into stream, try-with-resources
        try (Stream<String> text = Files.lines(Paths.get(fileName))) {
            text.forEach(line ->{
                Scanner scnr = new Scanner(line).useDelimiter("[,\r\n]+");
                customerList.add(new Customer(scnr.next(),scnr.next(),scnr.next()));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        CustomerDatabase customers = new CustomerDatabase();
        customers.readCustomerData("Lab10/records/Customerrecords.txt");

        System.out.println("\nSearching for Jack King..." + 
            "\n============================");
        Customer jack = customers.findCustomer("Jack", "King");

        if(jack != null) {
            System.out.println("Found record: " + jack);
        } else {
            System.out.println("Could not find Jack King");
        }

        System.out.println("\nSearching for Bill Gates..." + 
            "\n============================");
        Customer bill = customers.findCustomer("Bill", "Gates");

        if(bill != null) {
            System.out.println("Found record: " + bill);
        } else {
            System.out.println("Could not find Bill Gates");
        }

        System.out.println("\nFinding all customers who have a google email account" + 
            "\n=======================================================");
        ArrayList<Customer> domainCustomers = 
            customers.findCustomersWithSameEmailDomain("@google") ;
        System.out.println("Found " + domainCustomers.size() + " records total:");

        for(Customer c : domainCustomers) {
            System.out.println(c);
        }

    }

}
