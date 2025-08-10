package Manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import Log.Logger;

public class Manager {
    private static int managerount = 0;
    private static boolean isFirstRun = false;
    private static final Logger logger = Logger.getInstance("logFile.log");
    private  final String CSV_PATH = Paths.get("CSVs", "customer.csv").toString();
    private String managerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Manager(String name, String email, String phoneNumber, String address) {
        if (!isFirstRun) { // Avoid reset to 0 on every new run
            managerount = updateCount();
            isFirstRun = true;
        }
        this.managerId = String.format("M%04d", ++managerount);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    Manager(String managerId, String name, String email, String phoneNumber, String address) {
        this.managerId = managerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    private int updateCount() {
        int count = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(CSV_PATH))) {
            boolean isFirstLine = true;
            String line; 

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                count++;
            }
        }
        catch (IOException e) {
            System.err.println("Error reading customer CSV file: " + e.getMessage());
            logger.log("Error reading customer CSV file: " + e.getMessage(), 3);
        }
        return count;
    }

    public String getCustomerDetails() {
        return String.format("Customer ID: %s\nName: %s\nEmail: %s\nPhone: %s\nAddress: %s",
                managerId, name, email, phoneNumber, address);
    }

    public String getCustomerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
