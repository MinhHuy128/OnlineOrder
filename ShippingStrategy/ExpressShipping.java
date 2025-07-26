package ShippingStrategy;

public class ExpressShipping implements IShippingStrategy {
    private static final double SHIPPING_RATE = 0.15; // 15% of order total

    @Override
    public double calculateShippingFee(double orderTotal) {
        return orderTotal * SHIPPING_RATE;
    }

    @Override
    public String getMethodName() {
        return "Express Shipping";
    }
    
}
