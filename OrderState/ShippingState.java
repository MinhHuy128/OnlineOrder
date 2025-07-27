package OrderState;

import Order.Order;

public class ShippingState implements IOrderState {

    @Override
    public void processOrder(Order order) {
        System.out.println("Order is done processing and is ready for shipping");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cancelling order during delivering:\n");
        order.setState(new CancelledState());
        System.out.println("Order has been cancelled: " + order.getOrderDetails());
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Shipping order: " + order.getOrderDetails());
    }

    @Override
    public void deliverOrder(Order order) {
        order.setState(new DeliveredState()); // Transition to Delivered State
        System.out.println("Order has been delivered:\n" + order.getOrderDetails());
    }

    @Override
    public String getStateName() {
        return "Shipping State";
    }
    
}
