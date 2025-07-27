package ShippingStrategy;

public class EconomyShipping implements IShippingStrategy {
    private static final double SHIPPING_RATE = 0.05; // 5% of order total

    @Override
    public double calculateShippingFee(double orderTotal) {
        return orderTotal * SHIPPING_RATE;
    }

    @Override
    public String getMethodName() {
        return "Economy Shipping";
    }
    
}
