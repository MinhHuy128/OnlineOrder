public class ExpressShipping implements ShippingStrategy {
    @Override
    public double calculateShippingFee(double orderTotal) {
        return 40000 + 0.05 * orderTotal;
    }

    @Override
    public String getMethodName() {
        return "Giao hàng nhanh";
    }
}
