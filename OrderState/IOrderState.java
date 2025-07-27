package OrderState;
import Order.Order;

public interface IOrderState {
    void processOrder(Order order);
    void cancelOrder(Order order);
    void shipOrder(Order order);
    void deliverOrder(Order order);
    String getStateName();
}
