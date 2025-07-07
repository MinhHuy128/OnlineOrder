import java.util.ArrayList;
import java.util.List;

import model.Items;

class Order {
  private String orderID;
  private List<Items> items = new ArrayList<>();
  private OrderState currentState;
  private double total;
  private ShippingStrategy shippingStrategy;
  public Order(String orderID,double total){
    this.orderID = orderID;
    this.currentState = new NewOrderState();
    this.total = total;
  }
  public void addItem(Items item){ //thêm sản phẩm vào đơn hàng
        items.add(item);
    }
    public List<Items> getProductList(){ //lấy danh sách sản phẩm trong đơn hàng
        return this.items;
    }



  // Này là của state nha ae
  public void setState(OrderState state){
    this.currentState = state;
  }

  public OrderState getState(){
    return this.currentState;
  }
   public void process() {
        currentState.process(this);
    }
    
    public void cancel() {
        currentState.cancel(this);
    }
    
    public void ship() {
        currentState.ship(this);
    }
    
    public void deliver() {
        currentState.deliver(this);
    }

    public String getStatusName() {
        return this.currentState.getStatus();
    }

    

    public void setShippingStrategy(ShippingStrategy strategy) {
        this.shippingStrategy = strategy;
    }

    public double calculateShippingFee() {
        if (shippingStrategy == null) {
            throw new IllegalStateException("Chưa chọn phương thức vận chuyển.");
        }
        return shippingStrategy.calculateShippingFee(total);
    }

    public void printInvoice() {
        System.out.println("Tổng đơn hàng: " + total + " VND");
        System.out.println("Phương thức giao hàng: " + shippingStrategy.getMethodName());
        System.out.println("Phí vận chuyển: " + calculateShippingFee() + " VND");
        System.out.println("Tổng thanh toán: " + (total + calculateShippingFee()) + " VND");
    }

}
