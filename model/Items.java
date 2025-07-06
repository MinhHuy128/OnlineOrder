package model;

public class Items{
    private String itemName;
    private String itemId;
    private int quanttity;
    private double price;
    public Items(String itemId, String itemName, int quantity, double price){
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.quanttity = quantity;
    }
    public String getItemName(){
        return this.itemName;
    }
    public String getItemId(){
        return this.itemId;
    }
    public int getQuantity(){
        return this.quanttity;
    }
    public double getPrice(){
        return this.price;
    }
}
