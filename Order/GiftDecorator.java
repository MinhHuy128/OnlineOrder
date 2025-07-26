package Order;

public class GiftDecorator extends OrderDecorator {
    
    private double giftAmount;

    public GiftDecorator(Order order, double giftAmount) {
        super(order);
        this.giftAmount = giftAmount;
    }

    @Override
    public double calculateTotal() {
        double originalTotal = order.calculateTotal();
        return originalTotal + this.giftAmount; 
    }

    @Override
    public String getOrderDetails() {
        // Update wrapped order's total first
        double currentTotal = this.calculateTotal();
        order.setTotalAmount(currentTotal);
        
        // Get base details with correct state
        String baseDetails = order.getOrderDetails();
        
        // Add gift-specific information
        return baseDetails + String.format("\nGift Wrap Fee: $%.2f", this.giftAmount);
    }
    
}
