package Order;

public class InsuranceDecorator extends OrderDecorator {
    private double insuranceFee;
    
    public InsuranceDecorator(Order order, double insuranceFee) {
        super(order);
        this.insuranceFee = insuranceFee;
    }
    
    @Override
    public double calculateTotal() {
        return order.calculateTotal() + this.insuranceFee;
    }
    
    @Override
    public String getOrderDetails() {
        // Update wrapped order's total first
        double currentTotal = this.calculateTotal();
        order.setTotalAmount(currentTotal);
        
        // Get base details with correct state
        String baseDetails = order.getOrderDetails();
        
        // Add insurance-specific information
        return baseDetails + String.format("\nInsurance Fee: $%.2f", this.insuranceFee);
    }
}