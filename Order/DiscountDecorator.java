package Order;

public class DiscountDecorator extends OrderDecorator {
    
    private double discountRate;

    public DiscountDecorator(Order order, double discountRate) {
        super(order);
        this.discountRate = discountRate;
    }

    @Override
    public double calculateTotal() {
        double originalTotal = order.calculateTotal();
        return originalTotal - (originalTotal * discountRate);
    }

    @Override
    public String getOrderDetails() {
        // Update wrapped order's total first
        double currentTotal = this.calculateTotal();
        order.setTotalAmount(currentTotal);
        
        // Get base details with correct state
        String baseDetails = order.getOrderDetails();
        
        // Add discount-specific information
        return baseDetails + String.format("\nDiscount Applied: %.1f%%", this.discountRate * 100);
    }
}