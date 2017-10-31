import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerDatabase {
    ArrayList<Customer> customerList;

    public CustomerDatabase() {
        this.customerList = new ArrayList<>();
    }

    public Customer findCustomer(String firstName, String lastName) {
        return customerList.stream().filter(customer -> customer.firstName.equalsIgnoreCase(firstName) && customer.lastName.equalsIgnoreCase(lastName)).findFirst().orElse(null);
    }
    public ArrayList<Customer> findCustomersWithSameEmailDomain(String domain) {
        ArrayList<Customer> resulstList = customerList.stream().filter(customer -> customer.eMail.contains(domain)).collect(Collectors.toCollection(ArrayList::new));
        return resulstList;
    }
    public ArrayList<Customer> getDB() {
        return customerList;
    }

    public int getNumberCustomers () {
    return customerList.size();
    }

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

    public static void main(String[] args) {
        CustomerDatabase customers = new CustomerDatabase();
        customers.readCustomerData("records/Customerrecords.txt");

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
