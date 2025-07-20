package Decorator;

import Order.Order;

public class DiscountDecorator extends Wrapper {
    final double percent;

    public DiscountDecorator(Bill bill, Order order, double percent) {
        super(bill, order);
        this.percent = percent / 100;
    }

    @Override
    public double export() {
        double total = calculateTotal();
        if (total < 800000) {
            System.out.println("Counn't find any discount for this bill");
            return total;
        } else {
            double discountAmount = this.percent * total;
            double finalTotal = total - discountAmount;
            System.out.println(
                    String.format("Applying discount of: $%.2f\n", discountAmount) +
                            String.format("Bill after discount: $%.2f", finalTotal));
            return finalTotal;
        }
    }

    public double getTotal() {
        return this.calculateTotal();
    }
}
