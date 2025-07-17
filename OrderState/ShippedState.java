package OrderState;
import Order.*;
public class ShippedState implements OrderState{
    @Override
    public void process(Order order){
      System.out.println("Order already shipped");
    }

    @Override
    public void cancel(Order order){
      System.out.println("Cannot cancel order - already shippe");
    }

    @Override
    public void ship(Order order){
      System.out.println("Order already shipped");
    }
    @Override
    public void deliver(Order order){
      System.out.println("Delivering order...");
      order.setState(new DeliveredState());
    }

    @Override
    public String getStatus(){
        return "Shipped";
    }
}
