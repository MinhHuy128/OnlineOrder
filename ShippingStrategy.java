public interface ShippingStrategy {
    double calculateShippingFee(double orderTotal);
    String getMethodName();
}
