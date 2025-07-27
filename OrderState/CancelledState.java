package OrderState;
import Order.Order;

public class CancelledState implements IOrderState {

    @Override
    public void processOrder(Order order) {
        System.out.println("Cannot process a cancelled order: " + order.getOrderDetails());
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order is already cancelled: " + order.getOrderDetails());
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Cannot ship a cancelled order: " + order.getOrderDetails());
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Cannot deliver a cancelled order: " + order.getOrderDetails());
    }

    @Override
    public String getStateName() {
        return "Cancelled State";
    }
    
}