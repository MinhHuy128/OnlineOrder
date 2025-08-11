package Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Order.*;
import OrderState.IOrderState;
import Log.Logger;

public class Customer {
    private static int customerCount = 0;
    private static boolean isFirstRun = false;
    private static final Logger logger = Logger.getInstance("logFile.log");
    private  final String CSV_PATH = Paths.get("CSVs", "customer.csv").toString();
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private ArrayList<Order> listOfOrder = new ArrayList<>();

    public Customer(String name, String email, String phoneNumber, String address) {
        if (!isFirstRun) { // Avoid reset to 0 on every new run
            customerCount = updateCount();
            isFirstRun = true;
        }
        this.customerId = String.format("C%04d", ++customerCount);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    Customer(String customerId, String name, String email, String phoneNumber, String address) {
        this.customerId = customerId;
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
                customerId, name, email, phoneNumber, address);
    }

    public String getCustomerId() {
        return customerId;
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

    public void addOrderList(Order order){
        this.listOfOrder.add(order);
    }
    public void setState(String id, IOrderState state){
        for(Order i : this.listOfOrder){
            if(i.getOrderId().equalsIgnoreCase(id)){
                i.setState(state);
                System.out.println("Order State is set to: "+i.getCurrentState().getStateName());
            }
        }
    }
    public void delOrder(){
        this.listOfOrder.remove(this.listOfOrder.size()-1);
    }

    public List<Order> getOrderList(){
        return this.listOfOrder;
    }
}
