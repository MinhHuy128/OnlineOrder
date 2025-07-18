package StrateggyShipping;

public class StandardShipping implements ShippingStrategy {
    @Override
    public double calculateShippingFee(double orderTotal) {
        return 20000;
    }

    @Override
    public String getMethodName() {
        return "Standard Shipping";
    }
}
