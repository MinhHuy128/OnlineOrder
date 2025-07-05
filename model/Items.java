package model;

public class Items{
    private String productName;
    private String productId;
    private int quanttity;
    private double price;
    public Items(String productId, String productName, int quantity, double price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quanttity = quantity;
    }
    public String getProductName(){
        return this.productName;
    }
    public String getProductId(){
        return this.productId;
    }
    public int getQuantity(){
        return this.quanttity;
    }
    public double getPrice(){
        return this.price;
    }
}
