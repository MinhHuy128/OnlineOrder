package Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

abstract public class Product {
    private String color;
    private double price;
    private double length;
    private double width;
    private int amountInStore;
    private String productName;
    private String size;
    private static final String CSV_PATH = "item_list.csv";

    public Product(String productName, String color, double price, double length, double width, String size, int amountInStore) {
        this.productName = productName;
        this.color = color;
        this.price = price;
        this.length = length;
        this.width = width;
        this.size = size;
        this.amountInStore = amountInStore;
        this.updateCSV(productName, color, price, length, width, size, amountInStore);
    }

    public void updateCSV() {
        // Update the product details without changing the product name
        this.updateCSV(this.productName);
    }

    public void updateCSV(String productName, String color, double price, double length, double width, String size, int amountInStore) {
        String oldProductName = this.productName; // Store the old product name for updating
        this.productName = productName;
        // Update the product details
        this.color = color;
        this.price = price;
        this.length = length;
        this.width = width;
        this.size = size;
        this.amountInStore = amountInStore;
        this.updateCSV(oldProductName);
    }

    public void updateCSV(String oldProductName) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.productName).append(",")
            .append(this.color).append(",")
            .append(this.price).append(",")
            .append(this.length).append(",")
            .append(this.width).append(",")
            .append(this.size).append(",")
            .append(this.amountInStore).append("\n");
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_PATH, false))) {
            // Only change the line that needs to be updated not the entire file
            // Identify the line to be updated and replace it with the new details
            try (BufferedReader reader = new BufferedReader(new FileReader(CSV_PATH))) {
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(oldProductName + ",")) {
                        // Found the line to update
                        writer.write(sb.toString());
                        found = true;
                    } else {
                        // Write the old line back
                        writer.write(line + "\n");
                    }
                }
                if (!found) {
                    // If the product was not found, append it
                    writer.write(sb.toString());
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void add(int amount) {
        this.update(amount);
    }
    
    public void remove(int amount) {
        this.update(-amount);
    }
    
    private void update(int amount) {
        this.amountInStore += amount;
        if (this.amountInStore < 0) {
            this.amountInStore = 0; // Ensure we don't go below zero
        }
        this.updateCSV();
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public String getSize() {
        return size;
    }

    public int getAmountInStore() {
        return amountInStore;
    }

    public String getProductName() {
        return productName;
    }

    public void setColor(String color) {
        this.color = color;
        this.updateCSV();
    }

    public void setPrice(double price) {
        this.price = price;
        this.updateCSV();
    }

    public void setLength(double length) {
        this.length = length;
        this.updateCSV();
    }

    public void setWidth(double width) {
        this.width = width;
        this.updateCSV();
    }

    public void setSize(String size) {
        this.size = size;
        this.updateCSV();
    }

    public void setAmountInStore(int amountInStore) {
        this.amountInStore = amountInStore;
        this.updateCSV();
    }

    public void setProductName(String productName) {
        this.productName = productName;
        this.updateCSV();
    }

    @Override
    public String toString() {
        return String.format(
            "Product Name: %s, Color: %s, Price: %.2f, Length: %.2f, Width: %.2f, Size: %s, Amount in Store: %d",
            productName, color, price, length, width, size, amountInStore
        );
    }
}