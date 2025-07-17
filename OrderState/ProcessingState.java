package OrderState;
import Order.*;

public class ProcessingState implements OrderState {
    @Override
    public void process(Order order){
      System.out.println("Processing order has been done");
    }

    @Override
    public void cancel(Order order){
      System.out.println("Order cancelled during processing");
      order.setState(new CancelledState());
    }

    @Override
    public void ship(Order order){
      System.out.println("Shipping order...");
      order.setState(new ShippedState());
    }    
    @Override
    public void deliver(Order order){
      System.out.println("Cannot deliver order - not shipped yet");
    }

    @Override
    public String getStatus(){
        return "Processing";
    }
}
