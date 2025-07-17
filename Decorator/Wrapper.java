package Decorator;

import java.util.Iterator;

import Order.Order;
import Product.Product;

abstract class Wrapper implements Bill {
    public Order order;
    protected Bill bill;
    public Wrapper(Bill bill, Order order) {
        this.order = order;
        this.bill = bill;
    }
    public double calculateTotal(){ //Tính tổng giá đơn hàng 
        Iterator<Product> r = this.order.getBuyedList().iterator();
        double total = 0;
        while (r.hasNext()) {
            Product n = r.next();
            total+=(Math.abs(n.getQuantity())*Math.abs(n.getPrice()));
        }
        return total;
    }

}