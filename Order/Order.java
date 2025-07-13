package Order;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Product.*;
import database.*;
public class Order {
  // private static int count = 0;
  // private String orderID;
  private List<Product> buyedItems = new ArrayList<>();
  private String file_path = "csvFile/item_list.csv";
  
  public void addItemInOrder(Product p){
    File_Writer fw = new File_Writer();
    List<String[]> li = fw.Reader(file_path);
    for(String[] i : li){
      if(p.getProductName().equals(i[0].trim())){
        this.buyedItems.add(p);
        return;
      }
    }
    System.out.println("We don't have this item in our stored");
  }
  public void printBuyedList(){
    // File_Writer fw = new File_Writer();
    // List<String[]> li = fw.Reader(file_path);
    Iterator<Product> i = this.buyedItems.iterator();
    System.out.println("=".repeat(50));
    System.out.printf("%-15s %-15s %-10s %-10s\n", "Item ID", "Item Name", "Quantity", "Price");
    while (i.hasNext()) {
      Product n = i.next();
      System.out.printf("%-15s %-10d %-10.2f\n", n.getProductName(), n.getQuantity(), n.getPrice());
    }
    System.out.println("=".repeat(50));
  }
  public List<Product> getBuyedList(){
    return this.buyedItems;
  }
}
