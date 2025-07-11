import model.*;
import database.*;

import java.util.List;
import java.util.Iterator;

class OnlineProcessingOrder extends ProcessingOrder{
    public boolean verifyCustomer(Customer customer){
        boolean result = true;
        File_Writer fw = new File_Writer();
        String file = "customer.csv";
        List<String[]> li = fw.Reader(file);
        try {
            for(String[] i : li){
                if(i[0].equals(customer.getCusId())){
                    System.out.println("You already have an account!");
                    result = false;
                }
            }
            if(result == true){
                customer.addCustomer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean verifyInventory(Order order){ //xác minh sản phẩm trong đơn hàng (số lượng hàng hóa lớn hơn 0)
        Iterator<Items> p = order.getProductList().iterator();
        while (p.hasNext()) {
            Items n = p.next();
            if(n.getQuantity()<0){
                return false;
            }
        }
        return true;
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
            System.out.printf("%-15s %-15s %-10s %-10.2f\n", n.getItemId(), n.getItemName(), n.getQuantity(), n.getPrice());
        }
        System.out.println("Total: "+processed.calculateTotal(order)+"(Not apply discount!)");
        System.out.println("=".repeat(60));
    }
    public void notifyCustomer(){ //thông báo cho khách hàng dựa trên trạng thái hiện tại của đơn hàng
        OrderState s = new ProcessingState();
        System.out.println(s.getStatus());
        switch (s.getStatus()) {
            case "New":
                System.out.println("Your Order is new");
                break;
            case "Processing":
                System.out.println("Your order is processing");
                break;
            case "Shipped":
                System.out.println("Your order is shipped");
                break;
            case "Delivered":
                System.out.println("Your order is delivered");
                break;
            case "Cancelled":
                System.out.println("Your order is cancelled");
                break;
            default:
                break;
        }
    }
}