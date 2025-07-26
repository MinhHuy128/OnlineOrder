package OrderState;
import Order.Order;

public class NewOrderState implements IOrderState {

    @Override
    public void processOrder(Order order) {
        System.out.println("Order is still in the making, can not process yet: " + order.getOrderDetails());
        order.setState(new ProcesingState());
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order cancelled:\n");
        order.setState(new CancelledState()); // Transition to Cancelled State
        System.out.println(order.getOrderDetails());
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Cannot ship a new order:\n" + order.getOrderDetails());
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Cannot deliver a new order:\n" + order.getOrderDetails());
    }

    @Override
    public String getStateName() {
        return "New Order State";
    }
    
}
