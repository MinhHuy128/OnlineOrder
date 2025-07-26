package ShippingStrategy;

public interface IShippingStrategy {
    public double calculateShippingFee(double orderTotal);
    public String getMethodName();
}
