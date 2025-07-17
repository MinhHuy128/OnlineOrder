package Product;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import database.File_Writer;

abstract public class Product {
    private String color;
    private double price;
    private double length;
    private double width;
    private int amountInStore;
    private String productName;
    private String size;
    private int quantity;
    private static final String CSV_PATH = "csvFile/item_list.csv";

    public void setProduct(String productName, String color, double price, double length, double width, String size,
            int amountInStore) {
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

    public void updateCSV(String productName, String color, double price, double length, double width, String size,
            int amountInStore) {
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

        File_Writer fw = new File_Writer();
        List<String[]> li = fw.Reader(CSV_PATH);
        BufferedWriter w = fw.Writer(CSV_PATH);
        try {
            for (String[] i : li) {
                if (oldProductName.equals(i[0].trim())) {
                    System.out.println("We already have this product in stored...");
                    return;
                }
            }
            w.write(sb.toString());
            w.close();
        } catch (IOException e) {
            System.out.println("Cannot Write in CSV File...");
            e.printStackTrace();
        }
    }

    // Khanh's Work
    public void setCustomerProduct(String productName, int quantity) {
        File_Writer fw = new File_Writer();
        List<String[]> li = fw.Reader(CSV_PATH);
        for (String[] i : li) {
            if (productName.equals(i[0])) {
                this.setProduct(i[0].trim(), i[1].trim(), DoubleParsing(i[2]), DoubleParsing(i[3]), DoubleParsing(i[4]),
                        i[5].trim(), IntegerParsing(i[6]));
                this.quantity = quantity;
                return;
            }
        }
        System.out.println("We don't have this product yet...");
    }

    public void getStoredList() {

        File_Writer fw = new File_Writer();
        List<String[]> li = fw.Reader(CSV_PATH);
        System.out.println("=".repeat(50));
        System.out.printf("%-15s %-10s %-10s\n", "Product Name", "In Stored", "Price");
        for (String[] i : li) {
            System.out.printf("%-15s %-10s %-10s\n", i[0], i[6], i[2]);
        }
        System.out.println("=".repeat(50));
    }

    public double DoubleParsing(String s) {
        double d = Double.parseDouble(s.trim());
        return d;
    }

    public int IntegerParsing(String s) {
        int i = Integer.parseInt(s.trim());
        return i;
    }

    public int getQuantity() {
        return this.quantity;
    }

    // End of Khanh's Work
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
                productName, color, price, length, width, size, amountInStore);
    }

}