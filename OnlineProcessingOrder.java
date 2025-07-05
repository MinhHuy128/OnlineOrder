import model.Items;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Date;

class OnlineProcessingOrder extends ProcessingOrder{

    public String verifyInventory(Order order){ //xác minh sản phẩm trong đơn hàng (số lượng hàng hóa lớn hơn 0)
        Iterator<Items> p = order.getProductList().iterator();
        while (p.hasNext()) {
            Items n = p.next();
            if(n.getQuantity()<0){
                return ("Quantity of items in your order is not valid!");
            }
        }
        return "Quantity of items in your order is valid!";
    }

    public double calculateTotal(Order order){ //Tính tổng giá đơn hàng 
        Iterator<Items> r = order.getProductList().iterator();
        double total = 0;
        while (r.hasNext()) {
            Items n = r.next();
            total+=(Math.abs(n.getQuantity())*Math.abs(n.getPrice()));
        }
        return total;
    }
    public void generateInvoice(Order order){ //in ra hóa đơn
        ProcessingOrder processed = new OnlineProcessingOrder();
        Iterator<Items> p = order.getProductList().iterator();
        System.out.println(" ".repeat(23)+"Order Invoices"+" ".repeat(23));
        System.out.println("=".repeat(60));
        System.out.printf("%-15s %-15s %-10s %-10s\n", "Product ID", "Product Name", "Quantity", "Price");
        while (p.hasNext()) {
            Items n = p.next();
            System.out.printf("%-15s %-15s %-10s %-10.2f\n", n.getProductId(), n.getProductName(), n.getQuantity(), n.getPrice());
        }
        System.out.println("Total: "+processed.calculateTotal(order)+"(Not apply discount!)");
        System.out.println("=".repeat(60));
    }
    public void notifyCustomer(){

    }
}