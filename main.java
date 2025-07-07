public class main {
    public static void main(String[] args) {
        Order o = new Order("1");
        ProcessingOrder p = new OnlineProcessingOrder();
        Order order = new Order("13");
        
        System.out.println("Current status: " + order.getStatusName());
        
        order.process();
        System.out.println("Status: " + order.getStatusName());
        
        order.ship();
        System.out.println("Status: " + order.getStatusName());
        
        order.deliver();
        System.out.println("Status : " + order.getStatusName());
        
        order.cancel();
        
        Order cancelledOrder = new Order("5");
        cancelledOrder.cancel();
        System.out.println("status: " + cancelledOrder.getStatusName());
    
    }
  }
