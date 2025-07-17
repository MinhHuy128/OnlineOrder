package OrderState;
import Order.*;

public class CancelledState implements OrderState{
    @Override
    public void process(Order order){
      System.out.println("Cannot process order - already cancelled");
    }

    @Override
    public void cancel(Order order){
      System.out.println("Order already cancelled");
    }

    @Override
    public void ship(Order order){
      System.out.println("Cannot ship order - already cancelled");
    }
    @Override
    public void deliver(Order order){
      System.out.println("Cannot deliver order - already cancelled");
    }


    public String getStatus(){
        return "Cancelled";
    }
}