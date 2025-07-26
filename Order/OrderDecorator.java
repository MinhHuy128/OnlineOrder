package Order;

import Product.Product;
import java.util.HashMap;
import OrderState.IOrderState;

public abstract class OrderDecorator extends Order {
    protected Order order;
    
    public OrderDecorator(Order order) {
        super(order.getCustomerOfThisOrder());
        this.order = order;
    }
    
    @Override
    public void setState(IOrderState state) {
        // Set state on wrapped order first (propagates down to base)
        order.setState(state);
        // Then set on this decorator
        this.currentState = state;
    }
    
    @Override
    public void setCurrentState(IOrderState state) {
        // Delegate to setState for consistency
        this.setState(state);
    }
    
    @Override
    public IOrderState getCurrentState() {
        return order.getCurrentState();
    }
    
    @Override
    public String getOrderId() {
        return order.getOrderId(); // Use wrapped order's ID
    }
    
    @Override
    public void processOrder() {
        order.processOrder();
    }
    
    @Override
    public void cancelOrder() {
        order.cancelOrder();
    }
    
    @Override
    public void shipOrder() {
        order.shipOrder();
    }
    
    @Override
    public void deliverOrder() {
        order.deliverOrder();
    }
    
    @Override
    public HashMap<Product, Integer> getProductsInCart() {
        return order.getProductsInCart();
    }
    
    @Override
    public void addProductToCart(Product product, int quantity) {
        order.addProductToCart(product, quantity);
    }
    
    @Override
    public abstract double calculateTotal();
    
    @Override
    public String getOrderDetails() {
        // Update the wrapped order's total amount first
        double currentTotal = this.calculateTotal();
        order.setTotalAmount(currentTotal);
        
        // Then get the details (which will now show correct state and total)
        return order.getOrderDetails();
    }
}