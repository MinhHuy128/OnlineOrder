package OrderState;
import Order.Order;

public class ProcessingState implements IOrderState {

    @Override
    public void processOrder(Order order) {
        System.out.println("Processing order:");
        order.setState(new ShippingState()); 
        System.out.println("Order processing completed. Moving to shipping.\n");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cancelling order during processing:\n");
        order.setState(new CancelledState());
        System.out.println(order.getOrderDetails());
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Order is still being processed. Cannot ship yet.\n");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order is not delivered yet, still processing:\n");
    }

    @Override
    public String getStateName() {
        return "Processing State";
    }
    
}
