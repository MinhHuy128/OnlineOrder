public class EconomyShipping implements ShippingStrategy {
    @Override
    public double calculateShippingFee(double orderTotal) {
        return orderTotal > 500000 ? 0 : 15000;
    }

    @Override
    public String getMethodName() {
        return "Giao hàng tiết kiệm";
    }
}
