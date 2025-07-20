package StrateggyShipping;

public class EconomyShipping implements ShippingStrategy {
    @Override
    public double calculateShippingFee(double orderTotal) {
        return orderTotal > 500000 ? 10000 : 15000;
    }

    @Override
    public String getMethodName() {
        return "Economy Shipping";
    }
}
