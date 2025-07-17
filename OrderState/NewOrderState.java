package OrderState;
import Order.*;

public class NewOrderState  implements OrderState{
    @Override
    public void process(Order order){
      System.out.println("Processing order...");
      order.setState(new ProcessingState()).process(order);
    }

    @Override
    public void cancel(Order order){
      System.out.println("Order cancelled");
      order.setState(new CancelledState());
    }

    @Override
    public void ship(Order order){
      System.out.println("Cannot ship order - not processed yet");
    }
    @Override
    public void deliver(Order order){
      System.out.println("Cannot deliver order - not shipped yet");
    }

    @Override
    public String getStatus(){
        return "New";
    }


}