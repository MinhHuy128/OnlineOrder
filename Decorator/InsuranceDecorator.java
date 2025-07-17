package Decorator;

import Order.Order;

public class InsuranceDecorator extends Wrapper{
    public InsuranceDecorator(Bill bill, Order order) {
        super(bill, order);
    }

    @Override
    public double export() {
        double total = calculateTotal();
        double insuranceAmount = total * 0.02;
        double finalTotal = total + insuranceAmount;

        System.out.println(
            String.format("Applying insurance of: $%.2f\n", insuranceAmount) +
            String.format("Bill total after insurance: $%.2f", finalTotal)
        );
        return finalTotal;
    }
}
