package Product;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private final String type;
    private final String name;
    private final String size;
    private final String color;
    private final double price;
    private final double width;
    private final double height;
    private final double length;
    private final int quantity;
    private int amountInStock;
    private final Map<String, String> additionalAttributes;

    private Product(Builder builder) {
        this.type = builder.type;
        this.name = builder.name;
        this.size = builder.size;
        this.color = builder.color;
        this.price = builder.price;
        this.width = builder.width;
        this.height = builder.height;
        this.length = builder.length;
        this.quantity = builder.quantity;
        this.amountInStock = builder.quantity;
        this.additionalAttributes = new HashMap<>(builder.additionalAttributes);
    }

    public static class Builder {
        private String type;
        private String name;
        private String size;
        private String color;
        private double price;
        private double width;
        private double height;
        private double length;
        private int quantity;
        private Map<String, String> additionalAttributes = new HashMap<>();

        public Builder(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public Builder size(String size) { this.size = size; return this; }
        public Builder color(String color) { this.color = color; return this; }
        public Builder price(double price) { this.price = price; return this; }
        public Builder dimensions(double width, double height, double length) {
            this.width = width;
            this.height = height;
            this.length = length;
            return this;
        }
        public Builder quantity(int quantity) { this.quantity = quantity; return this; }
        
        public Builder addAttribute(String key, String value) {
            this.additionalAttributes.put(key, value);
            return this;
        }

        public Product build() {
            if (type == null || name == null) {
                throw new IllegalArgumentException("Type and name are required");
            }
            if (price < 0 || quantity < 0) {
                throw new IllegalArgumentException("Price and quantity must be non-negative");
            }
            return new Product(this);
        }
    }

    // Getters...
    public String getType() { return type; }
    public String getName() { return name; }
    public String getSize() { return size; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public double getLength() { return length; }
    public int getQuantity() { return quantity; }
    public int getAmountInStock() { return amountInStock; }
    
    public String getAttribute(String key) {
        return additionalAttributes.get(key);
    }
    
    public Map<String, String> getAllAttributes() {
        return new HashMap<>(additionalAttributes);
    }

    // Stock management methods...
    public void setAmountInStock(int amountInStock) {
        if (amountInStock < 0) {
            throw new IllegalArgumentException("Amount in stock cannot be negative");
        }
        this.amountInStock = amountInStock;
    }

    public void increaseStock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot increase stock by a negative amount");
        }
        this.amountInStock += amount;
    }

    public void decreaseStock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot decrease stock by a negative amount");
        }
        if (amount > this.amountInStock) {
            throw new IllegalArgumentException("Cannot decrease stock below zero");
        }
        this.amountInStock -= amount;
    }

    @Override
    public String toString() {
        return String.format("Product[type=%s, name=%s, size=%s, color=%s, price=%.2f, stock=%d]",
                type, name, size, color, price, amountInStock);
    }
}