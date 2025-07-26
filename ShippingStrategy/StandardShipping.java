package ShippingStrategy;

public class StandardShipping implements IShippingStrategy {
    private static final double SHIPPING_RATE = 0.07; // 7% of order total

    @Override
    public double calculateShippingFee(double orderTotal) {
        return orderTotal * SHIPPING_RATE;
    }

    @Override
    public String getMethodName() {
        return "Standard Shipping";
    }
    
}
