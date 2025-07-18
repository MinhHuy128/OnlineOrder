package StrateggyShipping;
public interface ShippingStrategy {
    public double calculateShippingFee(double orderTotal);
    public String getMethodName();
}
