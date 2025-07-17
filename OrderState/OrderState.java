package OrderState;

import Order.Order;
public interface OrderState {
    void process(Order order);
    void cancel(Order order);
    void ship(Order order);
    void deliver(Order order);
    String getStatus();  
}











