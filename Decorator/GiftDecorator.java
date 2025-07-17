package Decorator;

import Order.Order;

public class GiftDecorator extends Wrapper{
    public GiftDecorator(Bill bill, Order order) {
        super(bill, order);
    }

    @Override
    public double export() {
        double total = calculateTotal();
        System.out.println("Searching for gifts");
        System.out.println("Total bill: $" + total);
        if (total >= 500000) {
            System.out.println("Found 50 Gifts");
        }
        else {
            System.out.println("No Gift");
        }
        return total;
    }
}
