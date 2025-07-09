package model;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import database.File_Writer;

public class Items{
    private String itemName;
    private String itemId;
    private int storedQuantity;
    private int quantity;
    private double price;
    private String file = "item_list.csv";
    public void addItems(){
        File_Writer fw = new File_Writer();
        FileWriter writer = fw.Writer(this.file);
        List<String[]> li = fw.Reader(this.file);
        try {
            for(String[] i : li){
                if(i[0].equals(this.itemId)){
                    System.out.println("Cannot add item with the same id.");
                    return;
                }
            }
            writer.write(this.itemId+", ");
            writer.write(this.itemName+", ");
            writer.write(this.storedQuantity+", ");
            writer.write(this.price+"\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Item Insert Error!");
            e.printStackTrace();
        }
    }
    public void setItems(String itemId, String itemName, int storedQuantity, double price){
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.storedQuantity = storedQuantity;
    }
    public String getItemName(){
        return this.itemName;
    }
    public String getItemId(){
        return this.itemId;
    }
    public void getStoredList(){
        File_Writer fw = new File_Writer();
        List<String[]> li = fw.Reader(this.file);
        System.out.println("=".repeat(50));
        System.out.printf("%-15s %-15s %-10s %-10s\n", "Item ID", "Item Name", "In Stored", "Price");
        for(String[] i : li){
            System.out.printf("%-15s %-15s %-10s\n", i[0], i[1], i[2], i[3]);
        }
        System.out.println("=".repeat(50));
    }
    public double getPrice(){
        return this.price;
    }
}
