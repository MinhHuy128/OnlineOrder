interface OrderState {
    void process(Order order);
    void cancel(Order order);
    void ship(Order order);
    void deliver(Order order);
    String getStatus();  
}

class NewOrderState  implements OrderState{
    @Override
    public void process(Order order){
      System.out.println("Processing order...");
      order.setState(new ProcessingState());
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

class ProcessingState  implements OrderState{
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



class ShippedState  implements OrderState{
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




class DeliveredState  implements OrderState{
    @Override
    public void process(Order order){
      System.out.println("Order already delivered");
    }

    @Override
    public void cancel(Order order){
      System.out.println("Cannot cancel order - already delivered");
      
    }

    @Override
    public void ship(Order order){
      System.out.println("Order already delivered");
    }
    @Override
    public void deliver(Order order){
      System.out.println("Order already delivered");
    }

    @Override
    public String getStatus(){
        return "Delivered";
    }


}



class CancelledState   implements OrderState{
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










