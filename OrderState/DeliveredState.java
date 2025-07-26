package OrderState;
import Order.Order;

public class DeliveredState implements IOrderState {

    @Override
    public void processOrder(Order order) {
        System.out.println("Cannot process a delivered order: " + order.getOrderDetails());
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cannot cancel a delivered order: " + order.getOrderDetails());
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Cannot ship a delivered order: " + order.getOrderDetails());
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order is already delivered: " + order.getOrderDetails());
    }

    @Override
    public String getStateName() {
        return "Delivered State";
    }
    
}
