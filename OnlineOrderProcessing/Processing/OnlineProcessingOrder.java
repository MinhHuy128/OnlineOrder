package Processing;

import model.*;
import Order.Order;
import Product.Product;
import database.*;
import Log.*;
import Decorator.*;

import java.util.List;
import java.util.Iterator;

public class OnlineProcessingOrder extends ProcessingOrder implements Bill{
    Logger l = Logger.getInstance("fileLog.log");
    public boolean verifyCustomer(Customer customer){
        File_Writer fw = new File_Writer();
        String file = "csvFile/customer.csv";
        List<String[]> li = fw.Reader(file);
        try {
            for(String[] i : li){
                if(i[1].equals(customer.getLastName())){
                    System.out.println("You already have an account!");
                    return false;
                }
            }
            customer.addCustomer();
        } catch (Exception e) {
            l.log("Verify Customer Error", 3);
            e.printStackTrace();
        }
        return true;
    }

    public boolean verifyInventory(Order order){ //xác minh sản phẩm trong đơn hàng (số lượng hàng hóa lớn hơn 0)
        Iterator<Product> p = order.getBuyedList().iterator();
        while (p.hasNext()) {
            Product n = p.next();
            if(n.getQuantity()<0&&n.getQuantity()>n.getAmountInStore()){
                return false;
            }
        }
        return true;
    }
    // public double calculateTotal(Order order){ //Tính tổng giá đơn hàng 
    //     Iterator<Product> r = order.getBuyedList().iterator();
    //     double total = 0;
    //     while (r.hasNext()) {
    //         Product n = r.next();
    //         total+=(Math.abs(n.getQuantity())*Math.abs(n.getPrice()));
    //     }
    //     return total;
    // }

    public void generateInvoice(Order order){ //in ra hóa đơn
        Bill bill = new OnlineProcessingOrder();
        bill = new GiftDecorator(bill, order);
        
        Iterator<Product> p = order.getBuyedList().iterator();
        System.out.println(" ".repeat(23)+"Order Invoices"+" ".repeat(23));
        System.out.println("=".repeat(60));
        System.out.printf("%-15s %-15s %-10s %-10s\n", "Product ID", "Product Name", "Quantity", "Price");
        while (p.hasNext()) {
            Product n = p.next();
            System.out.printf("%-15s %-10s %-10.2f\n", n.getProductName(), n.getQuantity(), n.getPrice());
        }
        System.out.println("Total: "+bill.export()+"(Not apply discount!)");
        bill = new DiscountDecorator(bill, order, 10);
        bill.export();
        System.out.println("=".repeat(60));
    }
    public double export(){
        return 1.0;
    }
    // public void notifyCustomer(){ //thông báo cho khách hàng dựa trên trạng thái hiện tại của đơn hàng
    //     int i = 0;
    //     switch (i) {
    //         case "New":
    //             System.out.println("Your Order is new");
    //             break;
    //         case "Processing":
    //             System.out.println("Your order is processing");
    //             break;
    //         case "Shipped":
    //             System.out.println("Your order is shipped");
    //             break;
    //         case "Delivered":
    //             System.out.println("Your order is delivered");
    //             break;
    //         case "Cancelled":
    //             System.out.println("Your order is cancelled");
    //             break;
    //         default:
    //             break;
    //     }
    // }
}