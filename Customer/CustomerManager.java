package Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Log.Logger;

public class CustomerManager {
    private List<Customer> customerList;
    private String csvFilePath = "CSVs/customer.csv";
    private static final String CSV_HEADER = "CustomerId,Name,Email,PhoneNumber,Address";
    private static final Logger logger = Logger.getInstance("logFile.log");

    public CustomerManager() {
        this.customerList = new ArrayList<>();
        this.loadCustomersFromCSV();
    }

    public void addCustomer(Customer customer) {
        this.customerList.add(customer);
        this.saveCustomersToCSV(); // Persist changes
    }

    public Customer findCustomerById(String customerId) {
        return this.customerList.stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    public Customer findCustomerByPhone(String phoneNumber) {
        return this.customerList.stream()
                .filter(customer -> customer.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    public Customer findCustomerByEmail(String email) {
        return this.customerList.stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public boolean removeCustomer(String customerId) {
        boolean removed = this.customerList.removeIf(customer -> 
            customer.getCustomerId().equals(customerId));
        if (removed) {
            this.saveCustomersToCSV(); 
        }
        return removed;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(this.customerList); 
    }

    private void loadCustomersFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.csvFilePath))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header
                }
                
                String[] values = line.split(",");
                if (values.length >= 5) {
                    Customer customer = new Customer(
                        values[0].trim(),
                        values[1].trim(),
                        values[2].trim(),
                        values[3].trim(),
                        values[4].trim()
                    );
                    this.customerList.add(customer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading this.customerList from CSV: " + e.getMessage());
            // Create new file if it doesn't exist
            this.createCSVFile();
        }
    }

    // Save all this.customerList to CSV
    private void saveCustomersToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.csvFilePath))) {
            writer.write(CSV_HEADER);
            writer.newLine();
            
            for (Customer customer : this.customerList) {
                String customerData = String.format("%s,%s,%s,%s,%s",
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getAddress());
                writer.write(customerData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving this.customerList to CSV: " + e.getMessage());
            logger.log("Error saving customer list to CSV: " + e.getMessage(), 4);
        }
    }

    private void createCSVFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.csvFilePath))) {
            writer.write(CSV_HEADER);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
            logger.log("Error creating CSV file: " + e.getMessage(), 4);
        }
    }

}